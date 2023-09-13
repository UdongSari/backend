package com.udongsari.account.service;

import com.udongsari.account.dto.AccountDto;

import java.util.List;

public interface AccountService {
    Long create(AccountDto accountDto);
    AccountDto read(Long id);
    List<AccountDto> readAll();
    Long update(Long id, AccountDto accountDto);
    void delete(Long id);
}
