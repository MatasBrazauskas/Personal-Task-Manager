package com.example.demo.repository;

import com.example.demo.model.TaskDays;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface daysRepository extends JpaRepository<TaskDays, Long>
{
    @Query(value = "SELECT * FROM days WHERE task = :taskTitle", nativeQuery = true)
    List<TaskDays> findByTitle(@Param("taskTitle") String taskTitle);

    @Modifying
    @Query(value = "INSERT INTO days (task, week_day) VALUES(:task, :week_day)", nativeQuery = true)
    void addDay(@Param("task") String task,
                @Param("week_day") String week_day);
}
