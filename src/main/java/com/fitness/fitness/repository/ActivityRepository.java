package com.fitness.fitness.repository;

import com.fitness.fitness.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity,String> {
}
