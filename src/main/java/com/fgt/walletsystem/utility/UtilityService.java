package com.fgt.walletsystem.utility;

import com.fgt.walletsystem.enums.TransactionType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UtilityService {
    public static BigDecimal koboEquivalentOfAmount(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(100));
    }

    public static BigDecimal nairaEquivalentOfAmount(BigDecimal amount) {
        return amount.divide(BigDecimal.valueOf(100));
    }


    public static boolean determineCreditFromTransactionType(String transactionType) {
        return transactionType.equalsIgnoreCase(TransactionType.FUND.name());
    }

    public static boolean determineDebitFromTransactionType(String transactionType) {
        return transactionType.equalsIgnoreCase(TransactionType.PAYMENT.name());
    }

    public static String convertInputStreamToString(InputStream inputStream) throws IOException {

        String newLine = System.getProperty("line.separator");
        String result;
        try (Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines()) {
            result = lines.collect(Collectors.joining(newLine));
        }
        return result;

    }
}
