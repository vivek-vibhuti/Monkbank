package com.monk.monk.service;

import com.monk.monk.dto.*;
import com.monk.monk.entity.User;
import com.monk.monk.repository.UserRepository;
import com.monk.monk.util.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.monk.monk.dto.BankResponse;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final String ACCOUNT_NOT_EXIST_MESSAGE = "Account does not exist";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    Emailservice emailservice;

    public BankResponse EnquireyRequest(UserRequest userRequest) {
        // Check if account exists by account number
        boolean isAccount = userRepository.existsByAccountNumber(userRequest.getAccountNumber());

        if (!isAccount) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        // Retrieve account details
        Optional<User> founder = Optional.ofNullable(userRepository.findByAccountNumber(userRequest.getAccountNumber()));

        if (founder.isEmpty()) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        User user = founder.get();
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_FOUND_CODE)
                .responseMessage(AccountUtils.ACCOUNT_FOUND_SUCCESS)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(user.getAccountBalance())
                        .accountNumber(user.getAccountNumber())
                        .accountName(user.getFirstName() + " " + user.getLastName())
                        .build())
                .build();
    }

    @Override
    public BankResponse createAccount(UserRequest userRequest) {
        // Check for existing email
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();
        }


        User newUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .otherName(userRequest.getOtherName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .phoneNumber(userRequest.getPhoneNumber())
                .alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
                .status("Active")
                .build();

        // Save user and send email
        User savedUser = userRepository.save(newUser);
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(savedUser.getEmail())
                .subject("Account Creation")
                .messageBody("Congratulations! Your account has been created successfully.\n" +
                        "Account Details:\n" +
                        "Account Name: " + savedUser.getFirstName() + " " + savedUser.getLastName() +
                        (savedUser.getOtherName() != null ? " " + savedUser.getOtherName() : "") +
                        "\nAccount Number: " + savedUser.getAccountNumber())
                .build();

        emailservice.sendEamil(emailDetails);

        // Return response
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(savedUser.getAccountBalance())
                        .accountNumber(savedUser.getAccountNumber())
                        .accountName(savedUser.getFirstName() + " " + savedUser.getLastName())
                        .build())
                .build();
    }
    //balance enquiry , name enquiry , credit ,d ebit , credit transfer

    @Override
    public BankResponse balanceEnquiry(EnquireyRequest userRequest) {
        return null;
    }

    @Override
    public String nameEnquiry(EnquireyRequest request) {
        return "";



    }

    @Override
    public BankResponse creditAccount(CreditDebitRequest request) {
        boolean isAccountExist = userRepository.existsByAccountNumber(request.getAccountNumber());
        if (!isAccountExist) {
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }


        User userTocredit = userRepository.findByAccountNumber(request.getAccountNumber());

        userTocredit.setAccountBalance(userTocredit.getAccountBalance().add(request.getAmount()));
        userRepository.save(userTocredit);
        userTocredit.setAccountBalance(
                userTocredit.getAccountBalance().add(request.getAmount())

        );

        return BankResponse.builder()
                .responseMessage("Account Credited Successfully")
                .responseCode(AccountUtils.ACCOUNT_CREDIT_SUCCESS) // you can define a separate success code if needed
                .accountInfo(AccountInfo.builder()
                        .accountName(userTocredit.getFirstName() + " " +
                                userTocredit.getLastName() + " " +
                                (userTocredit.getOtherName() != null ? userTocredit.getOtherName() : ""))
                        .accountNumber(userTocredit.getAccountNumber())
                        .accountBalance(userTocredit.getAccountBalance())
                        .build())
                .build();
    }

    @Override
    public BankResponse debitAccount(CreditDebitRequest request) {
        return null;
    }
}
    