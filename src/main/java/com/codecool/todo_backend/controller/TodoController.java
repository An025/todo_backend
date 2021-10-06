package com.codecool.todo_backend.controller;

import com.codecool.todo_backend.model.Status;
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

    @GetMapping(path="{todoId}")
    public Todo getTodo(@PathVariable("todoId") Long todoId){
        return todoService.getTodo(todoId);
    }

    @DeleteMapping(path="{todoId}")
    public void deleteTodo(@PathVariable("todoId") Long todoId){
        todoService.deleteTodo(todoId);
    }

    @PutMapping(path="{todoId}")
    public void updateTodo(
            @PathVariable("todoId") Long todoId,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "status", required = false) Status status){
        todoService.updateTodo(todoId, title, status);
    }
}
