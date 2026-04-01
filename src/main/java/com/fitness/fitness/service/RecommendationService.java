package com.fitness.fitness.service;

import com.fitness.fitness.dto.RecommendationRequest;
import com.fitness.fitness.model.Activity;
import com.fitness.fitness.model.Recommendation;
import com.fitness.fitness.model.User;
import com.fitness.fitness.repository.ActivityRepository;
import com.fitness.fitness.repository.RecommendationRepository;
import com.fitness.fitness.repository.UserRepositoy;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepositoy userRepositoy;
    private  final ActivityRepository activityRepository;

    public Recommendation generateRecommendation(RecommendationRequest request) {
        User user =userRepositoy.findById(request.getUserId())
                .orElseThrow(()->new RuntimeException("Not a valid user"+ request.getUserId()));

        Activity activity= activityRepository.findById(request.getActivityId())
                .orElseThrow(()->new RuntimeException("Activity is not present" +request.getActivityId()));

        Recommendation recommendation=Recommendation.builder()
                .user(user)
                .activity(activity)
                .improvements(request.getImprovements())
                .suggestions(request.getSuggestions())
                .safety(request.getSafety())
                .build();

        return recommendationRepository.save(recommendation);

    }

    public List<Recommendation> getRecommendation(String userId) {
       return recommendationRepository.findByUserId(userId);
    }

    public List<Recommendation> getRecommendationByActivity(String activityId) {
        return recommendationRepository.findByActivityId(activityId);
    }
}
