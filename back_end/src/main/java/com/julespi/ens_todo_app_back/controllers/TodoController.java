package com.julespi.ens_todo_app_back.controllers;

import com.julespi.ens_todo_app_back.dto.TodoDto;
import com.julespi.ens_todo_app_back.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    // TODO voalr esto
    @RequestMapping(value = "api/prueba", method= RequestMethod.GET)
    public ResponseEntity<String> prueba(){
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

    @RequestMapping(value = "api/todo", method=RequestMethod.POST)
    public ResponseEntity<TodoDto> prueba(@RequestBody TodoDto todoDto){
        TodoDto newTodoDto = todoService.addTodo(todoDto);
        return new ResponseEntity<>(newTodoDto,HttpStatus.CREATED);
    }
}
