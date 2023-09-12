package com.udongsari.account.controller;

import com.udongsari.dto.AccountDto;
import com.udongsari.account.service.AccountService;
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
    public final AccountService accountService;

    @PostMapping
    public ResponseEntity<Map<String, Long>> createAccount(@RequestBody AccountDto accountDto) {
        Long id = accountService.create(accountDto);

        return ResponseEntity.ok(Collections.singletonMap("id", id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> readAccount(@PathVariable("id") Long id) {
        AccountDto accountDto = accountService.read(id);

        return ResponseEntity.status(accountDto == null ? HttpStatus.UNPROCESSABLE_ENTITY : HttpStatus.OK)
                .body(accountDto);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> readAccountAll() {
        List<AccountDto> accountDtoList = accountService.readAll();

        return ResponseEntity.ok(accountDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Long>> updateAccount(
            @PathVariable("id") Long id,
            @RequestBody AccountDto accountDto
    ){
        Long updatedUserId = accountService.update(id, accountDto);

        return ResponseEntity.ok(Collections.singletonMap("id", updatedUserId));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") Long id) {
        accountService.delete(id);

        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
