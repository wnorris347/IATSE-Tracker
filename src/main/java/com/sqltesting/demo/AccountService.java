package com.sqltesting.demo;

import org.hibernate.internal.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String createAccount(String firstName, String lastName, String username, String password){
        Account n = new Account();
        n.setUsername(username);
        n.setPassword(passwordEncoder.encode(password));
        n.setFirstName(firstName);
        n.setLastName(lastName);
        n.setRole("USER");
        accountRepository.save(n);
        return "Saved";
    }

    public Account updateAccount(String username, String firstName, String lastName, String oldUsername){
        Account account = accountRepository.findByUsername(oldUsername);
        Account newName = accountRepository.findByUsername(username);
        if(account == null || (newName != null && !username.equals(oldUsername))){
            return null;
        }
        Account n = new Account();
        n.setId(account.getId());
        n.setUsername(username);
        n.setPassword(account.getPassword());
        n.setFirstName(firstName);
        n.setLastName(lastName);
        n.setRole("USER");
        accountRepository.save(n);
        return n;
    }

    public Iterable<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account findByUsername(String username){
        return accountRepository.findByUsername(username);
    }
}
