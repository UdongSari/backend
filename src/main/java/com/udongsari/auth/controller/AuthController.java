package com.udongsari.auth.controller;

import com.udongsari.account.dto.AccountDto;
import com.udongsari.account.entity.Account;
import com.udongsari.auth.service.AuthServiceImpl;
import com.udongsari.configure.details.PrincipalDetails;
import com.udongsari.exception.DuplicateUsernameException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl authServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Long>> register(
            @RequestBody AccountDto accountDto
            ) {
        try {
            Long id = authServiceImpl.signUpAccount(accountDto);

            return ResponseEntity.status(HttpStatus.OK)
                    .header("Location", "/")
                    .body(Collections.singletonMap("id", id));
        } catch (DuplicateUsernameException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("id", null));
        }
    }


    @GetMapping("/user/read")
    public ResponseEntity<Account> readAccount(Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.OK)
                .body(principal.getUser());
    }
}
