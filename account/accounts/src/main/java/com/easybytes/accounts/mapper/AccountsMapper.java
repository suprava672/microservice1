package com.easybytes.accounts.mapper;

import com.easybytes.accounts.dto.AccountsDto;
import com.easybytes.accounts.entity.Accounts;

public class AccountsMapper {
    public static AccountsDto mapToAcoountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accountsDto.getAccountNumber());
        accountsDto.setAccountType(accountsDto.getAccountType());
        accountsDto.setBranchAddress(accountsDto.getBranchAddress());
        return accountsDto;
    }

    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accounts.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}