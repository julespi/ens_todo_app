package com.julespi.ens_todo_app_back.services;


import com.julespi.ens_todo_app_back.dao.TodoDaoImp;
import com.julespi.ens_todo_app_back.dto.TodoDto;
import com.julespi.ens_todo_app_back.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private void mapTodoToTodoDto(Todo todo, TodoDto todoDto) {
        todoDto.setId(todo.getId());
        todoDto.setName(todo.getName());
        todoDto.setCompleted(todo.getCompleted());
    }

    private void mapTodoDtoToTodo(TodoDto todoDto, Todo todo) {
        todo.setName(todoDto.getName());
        todo.setCompleted(todo.getCompleted());
    }
}
