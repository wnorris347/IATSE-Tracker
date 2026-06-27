package com.sqltesting.demo;

import org.hibernate.internal.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/accounts")
public class AccountController {
    AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("")
    public String addAccount(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String username, @RequestParam String password) {
        return accountService.createAccount(firstName, lastName, username, password);
    }

    @GetMapping("")
    public Iterable<Account> getAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{username}")
    public Account getAccount(@PathVariable String username) {
        return accountService.findByUsername(username);
    }

    @PutMapping("/{username}")
    public Account updateAccount(@PathVariable String username, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String newUsername) {
        return accountService.updateAccount(newUsername, firstName, lastName, username);
    }
}
