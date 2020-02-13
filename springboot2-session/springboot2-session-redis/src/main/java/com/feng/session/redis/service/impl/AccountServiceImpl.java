package com.feng.session.redis.service.impl;

import com.feng.session.redis.entity.Account;
import com.feng.session.redis.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    public Account add(Account account) {
        return null;
    }

    @Override
    public boolean login(Account account) {
        /*Account account = accountMapper.selectByName(loginAccount.getName());
        if(account.getPasswd().equals(loginAccount.getPasswd())) {
            account.setPasswd("");
            loginAccount = account;
            return true;
        }*/
        if (null != account) {
            if ("test".equals(account.getName().toLowerCase(Locale.CHINESE))
                    && "123456".equals(account.getPasswd().toLowerCase(Locale.CHINESE))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Account> search() {
        return null;
    }
}
