package com.udongsari.auth.controller;

import com.udongsari.dto.AccountDto;
import com.udongsari.entity.Account;
import com.udongsari.auth.service.AuthService;
import com.udongsari.configure.details.PrincipalDetails;
import com.udongsari.exception.duplicateUsernameException;
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
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Long>> register(
            @RequestBody AccountDto accountDto
            ) {
        try {
            Long id = authService.signUpAccount(accountDto);

            return ResponseEntity.status(HttpStatus.OK)
                    .header("Location", "/")
                    .body(Collections.singletonMap("id", id));
        } catch (duplicateUsernameException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("id", null));
        }
    }

    @GetMapping("/check")
    public ResponseEntity<Account> check(Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.OK)
                .body(principal.getUser());
    }

}
