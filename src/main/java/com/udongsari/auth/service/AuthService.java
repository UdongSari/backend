package com.udongsari.auth.service;

import com.udongsari.account.dto.AccountDto;

public interface AuthService {
    Long signUpAccount(AccountDto accountDto);
    // TODO: 2023/09/14 이메일 찾기, 비밀번호 찾기, 로그아웃 
}
