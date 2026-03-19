package com.fitness.fitness.service;

import com.fitness.fitness.dto.RegisterRequest;
import com.fitness.fitness.dto.UserResponse;
import com.fitness.fitness.model.User;
import com.fitness.fitness.repository.UserRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class UserServices {

    private final UserRepositoy userRepositoy;

    public UserServices(UserRepositoy userRepositoy) {
        this.userRepositoy = userRepositoy;
    }

    public UserResponse register(RegisterRequest request) {

        User user=User.builder()
                .email(request.getEmail())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .build();

    /*    User user=new User(
                null,
                request.getFirstname(),
                request.getLastname(),
                request.getEmail(),
                request.getPassword(),
                Instant.parse("2026-12-03T10:15:30.208Z")
                        .atZone(ZoneOffset.UTC)
                        .toLocalDateTime(),
                Instant.parse("2026-12-03T10:15:30.208Z")
                        .atZone(ZoneOffset.UTC)
                        .toLocalDateTime(),
                List.of(),
                List.of()
        );
*/
        User savedUser= userRepositoy.save(user);
        return mapToResponse(savedUser);
    }

    public void deleteUser(String userid){
        userRepositoy.deleteById(userid);
    }


    private UserResponse mapToResponse(User savedUser) {
        UserResponse response= new UserResponse();
        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setFirstname(savedUser.getFirstname());
        response.setLastname(savedUser.getLastname());
        response.setPassword(savedUser.getPassword());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());

        return response;
    }
}

