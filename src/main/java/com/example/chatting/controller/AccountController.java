package com.example.chatting.controller;

import com.example.chatting.database.AccountRepository;
import com.example.chatting.database.User;
import com.example.chatting.model.AccountRequest;
import com.example.chatting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/api/getAllAccount")
    public List<User> getAllAccount(){
        return accountService.getAllAccount();
    }
    @GetMapping("/api/getAccountById/{id}")
    public User getAccountById(@PathVariable int id){
        return accountService.getAccount(id);
    }

    @PostMapping("/api/saveAccount")
    public String saveAccount(@RequestBody AccountRequest acc){
        return accountService.saveAccount(acc);
    }

    @PatchMapping("/api/updateAccount/{email}")
    public String updateAccount(@PathVariable String email){
        return accountService.updateAccount(email);
    }

    @DeleteMapping("/api/deleteAccount/{id}")
    public void deleteAccount(@PathVariable int id){
         accountService.deleteAccount(id);
    }

    @PostMapping("/api/forgot-password")
    public String forgotPassWord()
}
