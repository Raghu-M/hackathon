package com.cassini.mortgage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cassini.mortgage.entity.Account;
import com.cassini.mortgage.service.AccountService;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("/{accountNumber}")
	public ResponseEntity<List<Account>> getAccounts(@PathVariable Long accountNumber){
		return ResponseEntity.ok().body(accountService.getAccounts(accountNumber));
	}

}
