package com.monk.monk.dto;


public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
    BankResponse balanceEnquiry(EnquireyRequest userRequest);
    String nameEnquiry(EnquireyRequest userRequest);
}
