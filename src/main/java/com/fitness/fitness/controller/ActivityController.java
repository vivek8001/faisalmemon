package com.fitness.fitness.controller;

import com.fitness.fitness.dto.ActivityRequest;
import com.fitness.fitness.dto.ActivityResponse;
import com.fitness.fitness.model.Activity;
import com.fitness.fitness.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> saveActivity(@RequestBody ActivityRequest request){
        return ResponseEntity.ok(activityService.createActivity(request));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getUserActivity(
            @RequestHeader(value="X-User-ID") String userId){
        return ResponseEntity.ok(activityService.getUserActivity(userId));
    }


    @GetMapping("/{id}")
    public Activity getActivity(@PathVariable String id){
        return activityService.getActivityByid(id);
    }
}
