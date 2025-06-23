package com.example.demo.repository;

import com.example.demo.model.Entities;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Entities, Long>
{
    @Modifying
    @Transactional
    @Query("DELETE FROM Entities e WHERE e.title = :title")
    void deleteByTitle(@Param("title") String title);

    @Query(value = "SELECT * FROM tasks", nativeQuery = true)
    List<Entities> getAllTasks();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tasks (title, status, due_Date) VALUES(:title, :status, :dueDate)", nativeQuery = true)
    void insertTask(@Param("title") String title,
                    @Param("status") boolean status,
                    @Param("dueDate") LocalDate dueDate);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM tasks WHERE title = :titleName)", nativeQuery = true)
    long existsByTitle(@Param("titleName") String titleName);
}
