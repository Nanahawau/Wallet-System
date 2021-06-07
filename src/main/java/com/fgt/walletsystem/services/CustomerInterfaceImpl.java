package com.fgt.walletsystem.services;

import com.fgt.walletsystem.dtos.CreateWalletDTO;
import com.fgt.walletsystem.domains.paystack.CreateCustomerResponse;
import com.fgt.walletsystem.entities.User;
import com.fgt.walletsystem.entities.Wallet;
import com.fgt.walletsystem.enums.ResponseCode;
import com.fgt.walletsystem.models.Response;
import com.fgt.walletsystem.repositories.UserRepository;
import com.fgt.walletsystem.utility.http.RestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
public class CustomerInterfaceImpl implements CustomerInterface {
    @Value("${paystack_baseurl}")
    private String baseUrl;
    private final RestClient restClient;
    private final UserRepository userRepository;


    public CustomerInterfaceImpl(RestClient restClient,
                                 UserRepository userRepository) {
        this.restClient = restClient;
        this.userRepository = userRepository;
    }

    /**
     * Call to Paystack's Create Customer API
     * return type of Paystack's Create Customer API is a CreateCustomerResponse object
     * Creates a customer on your integration.
     * Save Customer Details to Database.
     * @param createWalletDTO
     * @return response object
     */
    @Override
    public Response createCustomer(CreateWalletDTO createWalletDTO) {
        Response response = new Response();
        try {
            Optional<CreateCustomerResponse> createCustomerResponse = this.restClient
                    .post(baseUrl + "/customer", createWalletDTO, CreateCustomerResponse.class);
            log.info("Create customer response Paystack :{}", createCustomerResponse);
            if (createCustomerResponse.isPresent() && createCustomerResponse.get().isStatus()) {
                response = save(createWalletDTO, createCustomerResponse.get().getCustomerData().getCustomerCode());
            } else {
                response.setResponseCode(ResponseCode.WALLET_CREATION_FAILED.getCode());
                response.setResponseMessage(ResponseCode.WALLET_CREATION_FAILED.getMessage());
                return response;
            }
        } catch (Exception exception) {
            log.error("Error on Create customer :{} ", exception.getMessage());
            response.setResponseCode(ResponseCode.ERROR.getCode());
            response.setResponseMessage(ResponseCode.ERROR.getMessage());
            return response;
        }
        return response;
    }


    /**
     * Save user related details to User table;
     * Initialize wallet with default object.
     * @param createWalletDTO
     * @param customerCode
     * @return
     */
    @Override
    public Response save(CreateWalletDTO createWalletDTO, String customerCode) {
        Response response = new Response();
        try {
            Optional<User> user = userRepository.findByEmail(createWalletDTO.getEmail());
            if (user.isPresent()) {
                response.setResponseCode(ResponseCode.WALLET_EXISTS.getCode());
                response.setResponseMessage(ResponseCode.WALLET_EXISTS.getMessage());
                return response;
            } else {
                User userObject = new User();
                userObject.setEmail(createWalletDTO.getEmail());
                userObject.setFirstName(createWalletDTO.getFirstName());
                userObject.setLastName(createWalletDTO.getLastName());
                userObject.setCustomerCode(customerCode);
                userObject.setWallet(new Wallet());
                userRepository.save(userObject);

                response.setResponseCode(ResponseCode.SUCCESS.getCode());
                response.setResponseMessage(ResponseCode.SUCCESS.getMessage());
                // TODO: Find a better way to do this
                //TODO: User id is null on wallet table
                response.setData(userRepository.findByEmail(createWalletDTO.getEmail()));
            }

        } catch (Exception exception) {
            log.error("Error on saving to DB :{} ", exception.getMessage());
            exception.printStackTrace();
            response.setResponseCode(ResponseCode.ERROR.getCode());
            response.setResponseMessage(ResponseCode.ERROR.getMessage());
            return response;
        }
        return response;
    }


}
