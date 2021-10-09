package com.codecool.todo_backend.repository;

import com.codecool.todo_backend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {

}
