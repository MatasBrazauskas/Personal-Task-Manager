package com.example.demo.repository;

import com.example.demo.model.TaskCompletionDTO;
import com.example.demo.model.Tasks;
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
public interface taskRepository extends JpaRepository<Tasks, Long> {

    @Query(value = "SELECT * FROM tasks", nativeQuery = true)
    List<Tasks> getAllTasks();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tasks (title, description, date) VALUES(:title, :description, :date)", nativeQuery = true)
    void insertTask(@Param("title") String title,
                    @Param("description") String description,
                    @Param("date") LocalDate date);

    @Query(value = "SELECT * FROM tasks WHERE title = :title", nativeQuery = true)
    Optional<Tasks> findByTitle(@Param("title")String title);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tasks WHERE title = :title", nativeQuery = true)
    void deleteByTitle(@Param("title") String title);

    @Modifying
    @Transactional
    @Query(value = "UPDATE TASKS SET title = :changedTitle WHERE title = :oldTitle", nativeQuery = true)
    void updateTitles(@Param("changedTitle") String changedTitle, @Param("oldTitle") String oldTitle);

    @Modifying
    @Transactional
    @Query("SELECT new com.example.demo.model.TaskCompletionDTO(t.title, d.status) " +
            "FROM Tasks t JOIN Days d ON d.task = t.title " +
            "WHERE d.weekDay = :day")
    List<TaskCompletionDTO> findTasksToComplete(@Param("day") String day);
}
