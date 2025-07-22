package com.monk.monk.service;

import com.monk.monk.dto.BankResponse;
import com.monk.monk.dto.CreditDebitRequest;
import com.monk.monk.dto.EnquireyRequest;
import com.monk.monk.dto.UserRequest;

public interface UserService {
 BankResponse createAccount(UserRequest userRequest);
 BankResponse balanceEnquiry(EnquireyRequest userRequest);
 String nameEnquiry(EnquireyRequest request);
 BankResponse creditAccount(CreditDebitRequest request);
 BankResponse debitAccount(CreditDebitRequest request);
}
