package com.codecool.todo_backend.repository;

import com.codecool.todo_backend.model.Status;
import com.codecool.todo_backend.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findTodoByTitle(String title);

    Optional<Todo> findTodoById(Long todoId);

    List<Todo> findAllByStatus(Status status);
}
