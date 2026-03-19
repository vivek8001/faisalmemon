package com.fitness.fitness.service;

import com.fitness.fitness.dto.ActivityRequest;
import com.fitness.fitness.dto.ActivityResponse;
import com.fitness.fitness.model.Activity;
import com.fitness.fitness.model.User;
import com.fitness.fitness.repository.ActivityRepository;
import com.fitness.fitness.repository.UserRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepositoy userRepositoy;

    public ActivityResponse createActivity(ActivityRequest request) {
        User user=userRepositoy.findById(request.getId())
                .orElseThrow(()->new RuntimeException("Invalid User: "+ request.getUserid()));
        Activity activity= Activity.builder()
                .user(user)
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurnt(request.getCaloriesBurned())
                .startTime(request.getStartedAt())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();
        Activity savedActivity = activityRepository.save(activity);
        return mapToResponse(savedActivity);
    }

    private ActivityResponse mapToResponse(Activity savedActivity) {
        ActivityResponse response= new ActivityResponse();
        response.setId(savedActivity.getId());
        response.setUserid(savedActivity.getUser().getId());
        response.setDuration(savedActivity.getDuration());
        response.setType(savedActivity.getType());
        response.setCaloriesBurned(savedActivity.getCaloriesBurnt());
        response.setStartTime(savedActivity.getStartTime());
        response.setAdditionalMetrics(savedActivity.getAdditionalMetrics());
        response.setCreatedAt(savedActivity.getCreatedAt());
        response.setUpdatedAt(savedActivity.getUpdatedAt());
        return response;
    }

    public Activity getActivityByid(String id) {
        return activityRepository.findById(id).orElseThrow(()->new RuntimeException("Not valid"));
    }

    public ResponseEntity<List<Activity>> getActivity(Activity activity) {
        return ResponseEntity.ok(activityRepository.findAll());
    }
}
