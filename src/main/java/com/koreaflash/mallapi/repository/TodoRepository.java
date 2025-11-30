package com.koreaflash.mallapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koreaflash.mallapi.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    
} 