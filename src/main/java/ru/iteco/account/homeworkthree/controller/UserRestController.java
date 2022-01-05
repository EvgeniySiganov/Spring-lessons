package ru.iteco.account.homeworkthree.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iteco.account.homeworkthree.model.BankBookDto;
import ru.iteco.account.homeworkthree.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/bank-book")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/by-user-id/{userId}", "/by-user-id/"})
    public ResponseEntity<List<BankBookDto>> getAllBBDto(@PathVariable Integer userId){
        return ResponseEntity
                .ok(userService.findAllBankBookDtoByUserId(userId));
    }

    @GetMapping({"/{bankBookId}", "/"})
    public ResponseEntity<BankBookDto> getBBDto(@PathVariable Integer bankBookId){
        return ResponseEntity
                .ok(userService.findBankBookDto(bankBookId));
    }

    @PostMapping
    public ResponseEntity<BankBookDto> createBBDto(@RequestBody BankBookDto bankBookDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.create(bankBookDto));
    }

    @PutMapping
    public ResponseEntity<BankBookDto> updateBBDto(@RequestBody BankBookDto bankBookDto){
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
