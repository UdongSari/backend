package com.udongsari.account.service;


import com.udongsari.dto.AccountDto;
import com.udongsari.entity.Account;
import com.udongsari.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Long create(AccountDto accountDto) {
        Account account = accountRepository.save(accountDto.toEntity());
        return account.getId();
    }

    public AccountDto read(Long id) {
        Optional<Account> userAccountOptional = accountRepository.findById(id);

        if (userAccountOptional.isEmpty()) {
            throw new NotFoundException(" * 존재하지 않는 ID");
        }

        return userAccountOptional.get().toDto();
    }

    public List<AccountDto> readAll() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDto> accountDtos = new ArrayList<>();

        for (Account account : accounts) {
            AccountDto accountDto = account.toDto();
            accountDtos.add(accountDto);
        }

        return accountDtos;
    }

    public Long update(Long id, AccountDto accountDto) {
        Optional<Account> targetedUserAccountOptional = accountRepository.findById(id);

        if (targetedUserAccountOptional.isEmpty()) {
            throw new NotFoundException(" * 존재하지 않는 ID");
        }

        Account targetedAccount = targetedUserAccountOptional.get();

        BeanUtils.copyProperties(accountDto, targetedAccount, "id");
        Account updatedAccount = accountRepository.save(targetedAccount);

        return updatedAccount.getId();
    }

    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
