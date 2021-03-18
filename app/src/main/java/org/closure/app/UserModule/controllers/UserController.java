package org.closure.app.UserModule.controllers;

import java.util.List;

import org.closure.app.UserModule.dto.UserRequest;
import org.closure.app.UserModule.dto.UserResponse;
import org.closure.app.UserModule.models.UserModel;
import org.closure.app.UserModule.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(path = "/v2/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value="/add")
    public ResponseEntity<UserResponse> getMethodName(@RequestBody UserRequest request) {
        System.out.println(request.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(request));
    }

    @PutMapping(value="/signin")
    public ResponseEntity<UserResponse> signin
    (
        @RequestParam(name = "email") String email, 
        @RequestParam(name = "password") String password)
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signin(email, password));
    }
    
    @DeleteMapping(path = "/delete")
    public boolean delete
    (
        @RequestParam(name = "id")  Long id,
        @RequestParam(name = "password") String password)
    {
        return userService.delete(id, password);
    }

    @PostMapping(value="/update")
    public ResponseEntity<UserModel> updaEntity(@RequestBody UserModel model) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.edit(model));
    }
    
    @GetMapping(value="/signout")
    public boolean signout
    (
        @RequestParam(name = "id") Long id,
        @RequestParam(name = "name") String name) 
    {
        return userService.signout(id, name);
    }
    
    @GetMapping(value="/search")
    public ResponseEntity<List<UserResponse>> getMethodName(@RequestParam(name = "value") String value) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.search(value));
    }
    
}
