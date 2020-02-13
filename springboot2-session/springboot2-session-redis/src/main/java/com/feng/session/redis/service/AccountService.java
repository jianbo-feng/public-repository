package com.feng.session.redis.service;


import com.feng.session.redis.entity.Account;
import java.util.List;

public interface AccountService {

    Account add(Account account);

    boolean login(Account account);

    List<Account> search();
}
