package com.easybytes.accounts.service;

import com.easybytes.accounts.constants.AccountsConstant;
import com.easybytes.accounts.dto.AccountsDto;
import com.easybytes.accounts.dto.CustomerDto;
import com.easybytes.accounts.entity.Accounts;
import com.easybytes.accounts.entity.Customer;
import com.easybytes.accounts.exception.CustomerAlreadyExistException;
import com.easybytes.accounts.exception.ResourceNotFoundException;
import com.easybytes.accounts.mapper.AccountsMapper;
import com.easybytes.accounts.mapper.CustomerMapper;
import com.easybytes.accounts.repository.AccountsRepository;
import com.easybytes.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
@Log4j2
@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDto customerDto) {
        System.out.println(customerDto);
        Customer customer=CustomerMapper.mapToCustomer(customerDto,new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        System.out.println(optionalCustomer);
        log.info("optionalCustomer-"+optionalCustomer);
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistException("Customer  already present with the given MobileNumber:"
                    + customerDto.getMobileNumber());
        }

       System.out.println(customer);
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));

    }
    private Accounts createNewAccount(Customer customer){
        Accounts newaccounts = new Accounts();
        newaccounts.setCustomerId(customer.getCustomerId());
        long randomAcNumber=10000001111L + new Random().nextInt(8800000);
        newaccounts.setAccountNumber(randomAcNumber);
        newaccounts.setAccountType(AccountsConstant.SAVINGS);
        newaccounts.setBranchAddress(AccountsConstant.ADDRESS);
        return newaccounts;
    }
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAcoountsDto(accounts,new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;

        // Fetch AccountsDto from CustomerDto
        AccountsDto accountsDto = customerDto.getAccountsDto();

        // Corrected: Added null check for AccountsDto
        if (accountsDto != null) {

            // Corrected: Fetch Accounts by account number and handle not found scenario
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString()));

            // Corrected: Use mapping function to update Accounts from AccountsDto
            AccountsMapper.mapToAccounts(accountsDto, accounts);

            // Corrected: Save updated Accounts entity
            accounts = accountsRepository.save(accounts);

            // Corrected: Get customerId from the updated Accounts entity
            Long customerId = accounts.getCustomerId();

            // Corrected: Fetch Customer by customerId and handle not found scenario
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString()));

            // Corrected: Use mapping function to update Customer from CustomerDto
            CustomerMapper.mapToCustomer(customerDto, customer);

            // Corrected: Save updated Customer entity
            customerRepository.save(customer);

            isUpdated = true;  // Set flag to true indicating update was successful
        }

        return isUpdated;
    }


    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );
        accountsRepository.deleteById(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
