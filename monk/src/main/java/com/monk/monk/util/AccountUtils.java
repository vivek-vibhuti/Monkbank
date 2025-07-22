package com.monk.monk.util;

import java.time.Year;

public class AccountUtils {
    public static final String ACCOUNT_EXIST_CODE = "001";
    public static final String ACCOUNT_EXISTS_MESSAGE = "This user already exists.";
    public static final String ACCOUNT_CREATION_SUCCESS = "002";
    public static final String ACCOUNT_CREATION_MESSAGE = "Account has been created successfully.";
    public static final String ACCOUNT_NOT_EXIST_CODE = "004";
    public static final String ACCOUNT_NOT_EXIST_MESSAGE = "This user does not exist.";
    public static final String ACCOUNT_FOUND_SUCESS = "USER FOUND SUCESSFULLY";
    public static final String ACCOUNT_FOUND_CODE ="005" ;
    public static final String ACCOUNT_FOUND_SUCCESS ="sucessful" ;
    public static final String ACCOUNT_CREDIT_SUCCESS = "006" ;

    public static String generateAccountNumber() {
        // Current year and random six-digit number
        Year currentYear = Year.now();
        int min = 100000;
        int max = 999999;

        // Generate a random number
        int randomNumber = (int) (Math.random() * (max - min + 1)) + min;

        // Convert the current year to a string
        String year = String.valueOf(currentYear.getValue());
        String randomnumber = String.valueOf(randomNumber);

        // Combine year and random number to create account number
        return year + randomnumber;
    }
}
