package com.udongsari.auth.service;

import com.udongsari.dto.AccountDto;
import com.udongsari.entity.Account;
import com.udongsari.account.repository.AccountRepository;
import com.udongsari.exception.duplicateUsernameException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long signUpAccount(AccountDto accountDto) {
        if (accountRepository.findByUsername(accountDto.getUsername()).isPresent()){
            throw new duplicateUsernameException(" * username 중복 됨.");
        }

        accountDto.setId(null);
        accountDto.setRoles("ROLE_USER");
        accountDto.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));

        Account account = accountRepository.save(accountDto.toEntity());
        return account.getId();
    }

    // TODO: 2023/09/12 이메일 찾기, 비밀번호 찾기, 로그아웃
}
