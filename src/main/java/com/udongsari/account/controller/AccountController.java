package com.udongsari.account.controller;

import com.udongsari.account.dto.AccountDto;
import com.udongsari.account.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController("/api/vi/admin/account")
@RequiredArgsConstructor
public class AccountController {
    public final AccountServiceImpl accountServiceImpl;

    @PostMapping
    public ResponseEntity<Map<String, Long>> createAccount(@RequestBody AccountDto accountDto) {
        Long id = accountServiceImpl.create(accountDto);

        return ResponseEntity.ok(Collections.singletonMap("id", id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> readAccount(@PathVariable("id") Long id) {
        AccountDto accountDto = accountServiceImpl.read(id);

        return ResponseEntity.status(accountDto == null ? HttpStatus.UNPROCESSABLE_ENTITY : HttpStatus.OK)
                .body(accountDto);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> readAccountAll() {
        List<AccountDto> accountDtoList = accountServiceImpl.readAll();

        return ResponseEntity.ok(accountDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Long>> updateAccount(
            @PathVariable("id") Long id,
            @RequestBody AccountDto accountDto
    ){
        Long updatedUserId = accountServiceImpl.update(id, accountDto);

        return ResponseEntity.ok(Collections.singletonMap("id", updatedUserId));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") Long id) {
        accountServiceImpl.delete(id);

        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
