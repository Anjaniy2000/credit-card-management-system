package com.anjaniy.creditcardmanagementsystem.controllers;

import com.anjaniy.creditcardmanagementsystem.models.dto.request.CreateUserRequest;
import com.anjaniy.creditcardmanagementsystem.models.dto.UserDto;
import com.anjaniy.creditcardmanagementsystem.models.dto.request.GetUsersRequest;
import com.anjaniy.creditcardmanagementsystem.models.dto.response.GetUsersResponse;
import com.anjaniy.creditcardmanagementsystem.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/check")
    public ResponseEntity<String> check() {
        return new ResponseEntity<>("Serving.....", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid CreateUserRequest request) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") String id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<GetUsersResponse> getUsers(@RequestBody GetUsersRequest request) {
        return new ResponseEntity<>(userService.getUsers(request), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto request) {
        return new ResponseEntity<>(userService.updateUser(request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") String id) {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

}
