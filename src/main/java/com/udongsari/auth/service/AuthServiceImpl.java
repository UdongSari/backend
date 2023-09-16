package com.udongsari.auth.service;

import com.udongsari.account.dto.AccountDto;
import com.udongsari.account.entity.Account;
import com.udongsari.account.repository.AccountRepository;
import com.udongsari.exception.DuplicateUsernameException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Long signUpAccount(AccountDto accountDto) {
        if (accountRepository.findByUsername(accountDto.getUsername()).isPresent()){
            throw new DuplicateUsernameException(" * username 중복 됨.");
        }

        accountDto.setId(null);
        accountDto.setRoles("ROLE_USER");
        accountDto.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));

        Account account = accountRepository.save(accountDto.toEntity());
        return account.getId();
    }

}
