package com.easybytes.accounts.service;

import com.easybytes.accounts.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public interface IAccountsService {
    void createAccount(CustomerDto customerDto);
    CustomerDto fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);
}
