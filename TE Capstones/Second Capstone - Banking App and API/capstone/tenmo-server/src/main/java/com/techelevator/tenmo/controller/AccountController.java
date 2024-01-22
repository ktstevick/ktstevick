package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
public class AccountController {
    private AccountDao accountDao;

    public AccountController(JdbcAccountDao jdbcAccountDao) {
        this.accountDao = jdbcAccountDao;
    }

    @RequestMapping(path = "/accounts/{id}", method = RequestMethod.GET)
    public Account getAccountByAccountId(@PathVariable int id) {
        return accountDao.getAccountById(id);
    }

    @RequestMapping(path = "/accounts/{id}", method = RequestMethod.PUT)
    public Account updateAccount(@Valid @RequestBody Account account, @PathVariable int id) {
        return accountDao.updateAccount(account);
    }
}
