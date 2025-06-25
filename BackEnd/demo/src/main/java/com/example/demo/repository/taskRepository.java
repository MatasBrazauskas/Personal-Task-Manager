package com.example.demo.repository;

import com.example.demo.model.RepeatingTasks;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface taskRepository extends JpaRepository<RepeatingTasks, Long> {

    @Query(value = "SELECT * FROM tasks", nativeQuery = true)
    List<RepeatingTasks> getAllTasks();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tasks (title, description, date, status) VALUES(:title, :description, :date, :status)", nativeQuery = true)
    void insertTask(@Param("title") String title,
                    @Param("description") String description,
                    @Param("date") LocalDate date,
                    @Param("status") boolean status);

    @Query(value = "SELECT * FROM tasks WHERE title = :title", nativeQuery = true)
    Optional<RepeatingTasks> findByTitle(@Param("title")String title);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tasks WHERE title = :title", nativeQuery = true)
    void deleteByTitle(@Param("title") String title);

    @Modifying
    @Transactional
    @Query(value = "UPDATE TASKS SET title = :changedTitle WHERE title = :oldTitle", nativeQuery = true)
    void updateTitles(@Param("changedTitle") String changedTitle, @Param("oldTitle") String oldTitle);
}
