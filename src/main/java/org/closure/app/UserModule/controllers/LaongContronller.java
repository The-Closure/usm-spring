package org.closure.app.UserModule.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "/{lang}/user")
public class LaongContronller {
    
    @GetMapping(value="/test")
    public String getMethodName(@PathVariable(name = "lang") String lang) {
        return lang;
    }
    
}
