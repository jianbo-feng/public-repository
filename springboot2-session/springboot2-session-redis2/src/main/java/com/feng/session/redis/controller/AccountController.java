package com.feng.session.redis.controller;

import com.feng.session.redis.entity.Account;
import com.feng.session.redis.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@CrossOrigin(allowCredentials = "true", allowedHeaders = {"Authentication-Info", "content-type"}, exposedHeaders = {"Authentication-Info"})
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ResponseBody
    @RequestMapping("/add")
    public String insert(Account account) {
        account = accountService.add(account);
        return "success " + account;
    }

    @ResponseBody
    @RequestMapping("/login")
    public Boolean selectByPrimary(@RequestBody Account account, HttpSession session) {
        boolean success = accountService.login(account);
        if(success) {
            session.setAttribute("account", account);
        }
        System.err.println("sessionId = " + session.getId());
        return success;
    }

    @ResponseBody
    @RequestMapping("/search")
    public List<Account> search(HttpSession session) {
        System.out.println(session.getId());
        if(session.getAttribute("account") == null) {
            return null;
        }
        List<Account> accountList = accountService.search();
        return accountList;
    }

}
