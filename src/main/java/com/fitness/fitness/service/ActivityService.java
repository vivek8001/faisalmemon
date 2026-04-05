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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final UserRepositoy userRepositoy;

    public ActivityResponse createActivity(ActivityRequest request) {
        User user=userRepositoy.findById(request.getUserid())
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

    private ActivityResponse mapToResponse(Activity activity) {
        ActivityResponse response= new ActivityResponse();
        response.setId(activity.getId());
        response.setUserid(activity.getUser().getId());
        response.setDuration(activity.getDuration());
        response.setType(activity.getType());
        response.setCaloriesBurned(activity.getCaloriesBurnt());
        response.setStartTime(activity.getStartTime());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());
        return response;
    }

    public Activity getActivityByid(String id) {
        return activityRepository.findById(id).orElseThrow(()->new RuntimeException("Not valid"));
    }

    public List<ActivityResponse> getUserActivity(String userId) {
    //    return ResponseEntity.ok(activityRepository.findAll());
        List<Activity> activityList=activityRepository.findByUserId(userId);
        return activityList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
