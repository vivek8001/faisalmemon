package com.fitness.fitness.controller;

import com.fitness.fitness.dto.RecommendationRequest;
import com.fitness.fitness.model.Recommendation;
import com.fitness.fitness.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommendation")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping("/generate")
    public ResponseEntity<Recommendation> generateRecommendation(@RequestBody RecommendationRequest request){
        return ResponseEntity.ok(recommendationService.generateRecommendation(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getRecommendation(@PathVariable String userId){
        return ResponseEntity.ok(recommendationService.getRecommendation(userId));
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<Recommendation>> getRecommendationByActivity (@PathVariable String activityId){
        return ResponseEntity.ok(recommendationService.getRecommendationByActivity(activityId));
    }
}
