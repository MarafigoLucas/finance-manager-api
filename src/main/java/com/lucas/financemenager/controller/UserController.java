package com.lucas.financemenager.controller;

import com.lucas.financemenager.model.dto.UserRequest;
import com.lucas.financemenager.model.dto.UserResponse;
import com.lucas.financemenager.model.dto.UserUpdateRequest;
import com.lucas.financemenager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request) {

        UserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<UserResponse>listUsers(){
        return userService.listUsers();
    }

    @GetMapping("/{id}")
    private UserResponse getById(@PathVariable Long id){
        return userService.getUserById(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse>update(
            @PathVariable long id,
            @RequestBody UserUpdateRequest request
            ){
        UserResponse response = userService.updateUser(id,request);
        return ResponseEntity.ok(response);
    }
}
