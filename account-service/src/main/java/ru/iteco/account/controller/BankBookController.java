package ru.iteco.account.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.iteco.account.model.dto.BankBookDto;
import ru.iteco.account.service.BankBookUserService;
import ru.iteco.account.validation.Created;
import ru.iteco.account.validation.Update;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/bank-book")
@Validated
public class BankBookController {

    private final BankBookUserService userService;

    @GetMapping("/by-user-id/")
    public ResponseEntity<List<BankBookDto>> getAllBBDto(@CookieValue Integer userId,
                                                         @RequestHeader Map<String, String> cookies){
        log.info("Cookie: {}", cookies);
        return ResponseEntity
                .ok(userService.findAllBankBookDtoByUserId(userId));
    }

    @GetMapping({"/{bankBookId}", "/"})
    public ResponseEntity<BankBookDto> getBBDto(@PathVariable Integer bankBookId){
        return ResponseEntity
                .ok(userService.findBankBookDto(bankBookId));
    }

    @Validated(Created.class)
    @PostMapping
    public ResponseEntity<BankBookDto> createBBDto(@Valid @RequestBody BankBookDto bankBookDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.create(bankBookDto));
    }

    @Validated(Update.class)
    @PutMapping
    public ResponseEntity<BankBookDto> updateBBDto(@Valid @RequestBody BankBookDto bankBookDto){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(userService.update(bankBookDto));
    }

    @DeleteMapping("/{bankBookId}")
    public void deleteUser(@PathVariable Integer bankBookId){
        userService.deleteBankBook(bankBookId);
    }

    @DeleteMapping("by-user-id/{userId}")
    public void deleteAllUser(@PathVariable Integer userId){
        userService.deleteAllBankBookByUserId(userId);
    }

}
