package com.julespi.ens_todo_app_back.controllers;

import com.julespi.ens_todo_app_back.dto.TodoDto;
import com.julespi.ens_todo_app_back.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin//(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class TodoController {

    @Autowired
    private TodoService todoService;


    /**
     * Retrieve method
     * @param id
     * @return
     */
    @RequestMapping(value = "api/todo/{id}", method = RequestMethod.GET)
    public ResponseEntity<TodoDto> retrieveTodo(@PathVariable Long id) {
        TodoDto todoDto = todoService.getTodo(id);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    @RequestMapping(value = "api/todo", method = RequestMethod.GET)
    public ResponseEntity<List<TodoDto>> retrieveAllTodo() {
        List<TodoDto> todosDto = todoService.getAllTodos();
        return new ResponseEntity<>(todosDto, HttpStatus.OK);
    }

    // ADD
    @RequestMapping(value = "api/todo", method = RequestMethod.POST)
    public ResponseEntity<TodoDto> addTodo(@Valid @RequestBody TodoDto todoDto) {
        TodoDto newTodoDto = todoService.addTodo(todoDto);
        return new ResponseEntity<>(newTodoDto, HttpStatus.CREATED);
    }

    // UPDATE
    @RequestMapping(value = "api/todo/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TodoDto> updateTodo(@Valid @RequestBody TodoDto todoDto, @PathVariable Long id) {
        TodoDto updatedTodoDto = todoService.updateTodo(todoDto, id);
        return new ResponseEntity<>(updatedTodoDto, HttpStatus.OK);
    }

    // DELETE
    @RequestMapping(value = "api/todo/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String, String> handleRunTimeException(RuntimeException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put("message", errorMessage);
        });
        return errors;
    }
}
