package com.sqltesting.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public String createAccount(String firstName, String lastName, String username, String password){
        Account n = new Account();
        accountRepository.save(n);
        return "Saved";
    }

    public Iterable<Account> getAllAccounts(){
        return accountRepository.findAll();
    }
}
