package com.codecool.todo_backend.service;

import com.codecool.todo_backend.model.Todo;
import com.codecool.todo_backend.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    public final TodoRepository todoRepository;
    
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    public void addNewTodo(Todo todo) {
        Optional<Todo> todoOptional= todoRepository.findTodoByTitle(todo.getTitle());
        if(todoOptional.isPresent()){
            throw new IllegalStateException("todo taken");
        }
        todoRepository.save(todo);
    }

    public Todo getTodo(Long todoId) {
        Optional<Todo> todoOptional = todoRepository.findTodoById(todoId);
        if(todoOptional.isPresent()){
            return todoOptional.get();
        }else{
            throw new IllegalStateException("Not found todo: " + todoId);
        }
    }
}
