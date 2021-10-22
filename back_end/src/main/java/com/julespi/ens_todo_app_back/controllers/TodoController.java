package com.julespi.ens_todo_app_back.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class TodoController {

    // TODO voalr esto
    @RequestMapping(value = "api/prueba", method=GET)
    public ResponseEntity<String> prueba(){
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }
}
