package com.codecool.todo_backend.service;

import com.codecool.todo_backend.model.Status;
import com.codecool.todo_backend.model.Todo;
import com.codecool.todo_backend.repository.TodoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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

    public void deleteTodo(Long todoId) {
        boolean exists = todoRepository.existsById(todoId);
        if(!exists){
            throw new IllegalStateException("Todo with id " + todoId + "doesn't exists.");
        }
        todoRepository.deleteById(todoId);
    }


    @Transactional
    public void updateTodo(Long todoId, String title, Status status) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalStateException(
                        "Todo with id " + todoId + " does not exists"
                ));
        if(title!=null && title.length() > 0 && !Objects.equals(todo.getTitle(), title)){
            todo.setTitle(title);
        }
        if(status != null  && !Objects.equals(todo.getStatus(), status)){
            todo.setStatus(status);
        }
        todoRepository.saveAndFlush(todo);
    }

    public void deleteAllCompleted() {
        List<Todo> allByStatus = todoRepository.findAllByStatus(Status.COMPLETE);
        for(Todo todo: allByStatus){
            deleteTodo(todo.getId());
        }
    }

    @Transactional
    public void toggleStatusById(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                ()-> new IllegalStateException(
                        "Todo with id " + todoId + " does not exists"
                ));

        todo.setStatus(todo.getStatus().equals(Status.COMPLETE)? Status.ACTIVE : Status.COMPLETE);
        System.out.println(todo);
        todoRepository.saveAndFlush(todo);
    }
}
