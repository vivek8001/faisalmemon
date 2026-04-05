package com.fitness.fitness.repository;

import com.fitness.fitness.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity,String> {
    List<Activity> findByUserId(String userId);
}
