package com.example.demo.repository;

import com.example.demo.model.Days;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface daysRepository extends JpaRepository<Days, Long>
{
    @Query(value = "SELECT * FROM days", nativeQuery = true)
    List<Days> getAllDays();

    @Query(value = "SELECT * FROM days WHERE task = :taskTitle", nativeQuery = true)
    List<Days> findByTitle(@Param("taskTitle") String taskTitle);

    @Modifying
    @Query(value = "INSERT INTO days (task, week_day, status) VALUES(:task, :week_day, 0)", nativeQuery = true)
    void addDay(@Param("task") String task,
                @Param("week_day") String week_day);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM days WHERE task = :taskTitle", nativeQuery = true)
    void deleteByTitle(@Param("taskTitle") String taskTitle);

    @Modifying
    @Transactional
    @Query(value = "UPDATE TASKS SET title = :changedTitle WHERE title = :oldTitle", nativeQuery = true)
    void updateTitles(@Param("changedTitle") String changedTitle, @Param("oldTitle") String oldTitle);

    @Modifying
    @Transactional
    @Query(value = "UPDATE days SET status = 0 WHERE week_day != :day", nativeQuery = true)
    void resetStatus(@Param("day")  String day);

    @Modifying
    @Transactional
    @Query(value = "UPDATE days SET status = 1 WHERE week_day = :day AND task = :title", nativeQuery = true)
    void updateStatus(@Param("day") String day, @Param("title") String title);
}
