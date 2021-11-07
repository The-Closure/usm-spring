package org.closure.app.FirebaseModule.controllers;

import org.closure.app.FirebaseModule.models.FirebaseRequest;
import org.closure.app.FirebaseModule.services.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(path = "/api/v2/firebase")
public class FirebaseController {
    
    @Autowired
    FirebaseService firebaseService;


    @PostMapping(value="/addToken")
    public ResponseEntity<Object> addTokenToUser(@RequestBody FirebaseRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(firebaseService.addTokenToUser(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        
    }
    
}
