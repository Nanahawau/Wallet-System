package com.fgt.walletsystem.services;

import com.fgt.walletsystem.dtos.InitiateTransactionDTO;
import com.fgt.walletsystem.assemblers.TransactionLogAssembler;
import com.fgt.walletsystem.domains.paystack.InitializeTransactionResponse;
import com.fgt.walletsystem.domains.paystack.VerifyTransactionResponse;
import com.fgt.walletsystem.entities.Transaction;
import com.fgt.walletsystem.entities.User;
import com.fgt.walletsystem.entities.Wallet;
import com.fgt.walletsystem.enums.ResponseCode;
import com.fgt.walletsystem.models.Response;
import com.fgt.walletsystem.repositories.TransactionRepository;
import com.fgt.walletsystem.repositories.UserRepository;
import com.fgt.walletsystem.repositories.WalletRepository;
import com.fgt.walletsystem.utility.UtilityService;
import com.fgt.walletsystem.utility.http.RestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
public class PaymentInterfaceImpl implements PaymentInterface {

    @Value("${paystack_baseurl}")
    private String baseUrl;

    private final RestClient restClient;
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final TransactionLogAssembler transactionLogAssembler;


    public PaymentInterfaceImpl(RestClient restClient,
                                TransactionRepository transactionRepository,
                                WalletRepository walletRepository,
                                UserRepository userRepository,
                                TransactionLogAssembler transactionLogAssembler) {
        this.restClient = restClient;
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
        this.transactionLogAssembler = transactionLogAssembler;
    }

    /**
     * Call to Paystack's Initialize Transaction API.
     * return type of Paystack's Initialize Transaction API is a InitializeTransactionResponse Object.
     * Initializes a transaction.
     * @param initiateTransactionDTO
     * @return void
     */
    @Override
    public Response initializeTransaction(InitiateTransactionDTO initiateTransactionDTO) {
        Response response = new Response();
        try {
            Optional<InitializeTransactionResponse> initializeTransactionResponse = this.restClient
                    .post(baseUrl + "/transaction/initialize", initiateTransactionDTO, InitializeTransactionResponse.class);
            log.info("Create customer response Paystack {}: ", initializeTransactionResponse);
            if (initializeTransactionResponse.isPresent() && initializeTransactionResponse.get().isStatus()) {
                log.info("Initialize Transaction Response {}: ", initializeTransactionResponse);
                Response transactionLogResponse = saveToTransactionLog(transactionLogAssembler.fromInitiateTransactionDTO(initiateTransactionDTO),
                        initiateTransactionDTO.getEmail());
                log.info("Transaction Log Response {}: ", transactionLogResponse);

                // TODO: Test this guy with several scenarios.
                if (!transactionLogResponse.getResponseCode().equalsIgnoreCase("000")) {
                  return transactionLogResponse;
                }
                response.setResponseCode(ResponseCode.SUCCESS.getCode());
                response.setResponseMessage(ResponseCode.SUCCESS.getMessage());
                response.setData(initializeTransactionResponse.get().getInitializeTransactionData());
            }
        } catch (Exception exception) {
            log.error("Error on Initialize Transaction {}: ", exception.getMessage());
            exception.printStackTrace();
            response.setResponseCode(ResponseCode.ERROR.getCode());
            response.setResponseMessage(ResponseCode.ERROR.getMessage());
            return response;
        }
        return response;
    }


    /**
     * Call to Paystack's Verify Transaction API.
     * return type of Paystack's Verify Transaction API is VerifyTransactionResponse.
     * Confirms status of a transaction.
     * Save Transaction information on Log Table and reflect change on Wallet Balance on Database.
     * @param reference
     * @return Response Object
     */
    @Override
    public Response verifyPayment(String reference) {
        Response response = new Response();
        try {
            Optional<VerifyTransactionResponse> verifyTransactionResponse = this
                    .restClient.get(baseUrl + "/transaction/verify/" + reference, VerifyTransactionResponse.class);
            log.info("Verify Payment Response :{}", verifyTransactionResponse);
            if (verifyTransactionResponse.isPresent()) {
                response = saveToWallet(verifyTransactionResponse.get());
            } else {
                response.setResponseCode(ResponseCode.VERIFICATION_FAILED.getCode());
                response.setResponseMessage(ResponseCode.VERIFICATION_FAILED.getMessage());
                return response;
            }
        } catch (Exception exception) {
            log.error("Error on Create customer {}: ", exception.getMessage());
            response.setResponseCode(ResponseCode.ERROR.getCode());
            response.setResponseMessage(ResponseCode.ERROR.getMessage());
            return response;
        }
        return response;
    }


    /**
     * Save change in wallet balance depending on Transaction Type i.e FUND or PAYMENT.
     * @param verifyTransactionResponse
     * @return Response Object
     */
    @Override
    public Response saveToWallet(VerifyTransactionResponse verifyTransactionResponse) {
        Response response = new Response();

        try {
            Optional <Transaction> transaction = Optional.ofNullable(transactionRepository
                    .getTransactionsByTransactionReference(verifyTransactionResponse.getTransactionData().getReference()));

            log.info("Transaction fetched {}: ", transaction);

            if(transaction.isPresent()) {
                if (transaction.get().isCreditWallet()) {
                    transaction.get().getWallet().setBalance(transaction.get().getWallet().getBalance().add(verifyTransactionResponse.getTransactionData().getAmount()));
                } else {
                    transaction.get().getWallet().setBalance(transaction.get().getWallet().getBalance().subtract(UtilityService.nairaEquivalentOfAmount(verifyTransactionResponse.getTransactionData().getAmount())));
                }

                transaction.get().setVerifyTransactionDate(verifyTransactionResponse.getTransactionData().getTransactionDate());
                transaction.get().setMessage(verifyTransactionResponse.getMessage());
                transaction.get().setFees(verifyTransactionResponse.getTransactionData().getFees());
                transaction.get().setCurrency(verifyTransactionResponse.getTransactionData().getCurrency());
                transaction.get().setIpAddress(verifyTransactionResponse.getTransactionData().getIpAddress());
                transaction.get().setChannel(verifyTransactionResponse.getTransactionData().getChannel());
                transaction.get().setStatus(verifyTransactionResponse.getTransactionData().getStatus());

                transactionRepository.save(transaction.get());
                response.setResponseCode(ResponseCode.SUCCESS.getCode());
                response.setResponseMessage(ResponseCode.SUCCESS.getMessage());
            }

        } catch (Exception exception) {
            log.error("Error on Save to wallet {}: ", exception.getMessage());
            exception.printStackTrace();
            response.setResponseCode(ResponseCode.ERROR.getCode());
            response.setResponseMessage(ResponseCode.ERROR.getMessage());
            return response;
        }
        return response;
    }


    /**
     * Save to Transaction Log table details of a Transaction.
     * @param transaction
     * @param email
     * @return Response Object
     */
    @Override
    public Response saveToTransactionLog(Transaction transaction, String email) {
        Response response = new Response();
        try {
            Optional<User> user = userRepository.findByEmail(email);
            if (!user.isPresent()) {
                response.setResponseCode(ResponseCode.WALLET_NOT_EXIST.getCode());
                response.setResponseMessage(ResponseCode.WALLET_NOT_EXIST.getMessage());
                return response;
            } else {
                Optional <Wallet> wallet = Optional.ofNullable(user.get().getWallet());
                if (!wallet.isPresent()) {
                    response.setResponseCode(ResponseCode.WALLET_NOT_EXIST.getCode());
                    response.setResponseMessage(ResponseCode.WALLET_NOT_EXIST.getMessage());
                    return response;
                }

                transaction.setWallet(wallet.get());
                transactionRepository.save(transaction);
                response.setResponseCode(ResponseCode.SUCCESS.getCode());
                response.setResponseMessage(ResponseCode.SUCCESS.getMessage());
            }

        } catch (Exception exception) {
            log.error("Error on Save to Transaction Log :{} ", exception.getMessage());
            exception.printStackTrace();
            response.setResponseCode(ResponseCode.ERROR.getCode());
            response.setResponseMessage(ResponseCode.ERROR.getMessage());
            return response;
        }
        return response;
    }





}
