package com.codecool.todo_backend.controller;

import com.codecool.todo_backend.model.Todo;
import com.codecool.todo_backend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/todo")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getTodos(){
        return todoService.getTodos();
    }

    @PostMapping
    public void addNewTodo(@RequestBody Todo todo){
        todoService.addNewTodo(todo);
    }
}