package com.koreaflash.mallapi.repository;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.koreaflash.mallapi.domain.Todo;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {
    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void test1() {
        Assertions.assertNotNull(todoRepository);
        log.info("TodoRepository: " + todoRepository.getClass().getName());
    }

    @Test
    public void testInsert() {
        Todo todo = Todo.builder()
                .title("Test Todo")
                .writer("user01")
                .dueDate(LocalDate.of(2025, 11, 30))
                .build();
        Todo result = todoRepository.save(todo);
        log.info("Inserted Todo: " + result);
    }

    @Test
    public void testRead() {
        Long tno = 1L; // 존재하는 tno로 변경 필요
        todoRepository.findById(tno).ifPresentOrElse(
                todo -> log.info("Read Todo: " + todo),
                () -> log.warn("Todo with tno " + tno + " not found.")
        );
    }

    @Test
    public void testUpdate() {
        Long tno = 1L; // 존재하는 tno로 변경 필요
        todoRepository.findById(tno).ifPresentOrElse(
                todo -> {
                    Todo updatedTodo = Todo.builder()
                            .tno(todo.getTno())
                            .title("Updated Title")
                            .content("Updated Content")
                            .writer(todo.getWriter())
                            .dueDate(todo.getDueDate())
                            .complete(true)
                            .build();
                    todoRepository.save(updatedTodo);
                    log.info("Updated Todo: " + updatedTodo);
                },
                () -> log.warn("Todo with tno " + tno + " not found for update.")
        );
    }
}
