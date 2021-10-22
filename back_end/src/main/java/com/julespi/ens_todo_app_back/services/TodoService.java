package com.julespi.ens_todo_app_back.services;


import com.julespi.ens_todo_app_back.dao.TodoDaoImp;
import com.julespi.ens_todo_app_back.dto.TodoDto;
import com.julespi.ens_todo_app_back.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoDaoImp todoDaoImp;

    public TodoDto addTodo(TodoDto todoDto){
        Todo todo = new Todo();
        mapTodoDtoToTodo(todoDto, todo);
        todo.setCompleted(false);
        todoDaoImp.save(todo);
        TodoDto newTodoDto = new TodoDto();
        mapTodoToTodoDto(todo,newTodoDto);
        return newTodoDto;
    }

    public TodoDto updateTodo(TodoDto todoDto, Long id) throws RuntimeException{
        Todo dbTodo = todoDaoImp.findById(Todo.class, id);

        mapTodoDtoToTodo(todoDto, dbTodo);
        todoDaoImp.update(dbTodo);
        TodoDto updatedTodoDto = new TodoDto();
        mapTodoToTodoDto(dbTodo,updatedTodoDto);
        return updatedTodoDto;
    }

    public void deleteTodo(Long id) {
        Todo dbTodo = todoDaoImp.findById(Todo.class, id);
        todoDaoImp.remove(dbTodo);
    }

    public TodoDto getTodo(Long id) {
        Todo dbTodo = todoDaoImp.findById(Todo.class, id);
        TodoDto todoDto = new TodoDto();
        mapTodoToTodoDto(dbTodo, todoDto);
        return todoDto;
    }

    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoDaoImp.list(Todo.class);
        List<TodoDto> todoDtos = new ArrayList<>();
        for(Todo todo:todos){
            TodoDto todoDto = new TodoDto();
            mapTodoToTodoDto(todo, todoDto);
            todoDtos.add(todoDto);
        }
        return todoDtos;
    }

    private void mapTodoToTodoDto(Todo todo, TodoDto todoDto) {
        todoDto.setId(todo.getId());
        todoDto.setName(todo.getName());
        todoDto.setCompleted(todo.getCompleted());
    }

    private void mapTodoDtoToTodo(TodoDto todoDto, Todo todo) {
        //todo.setId(todoDto.getId());
        todo.setName(todoDto.getName());
        todo.setCompleted(todoDto.getCompleted());
    }


}
