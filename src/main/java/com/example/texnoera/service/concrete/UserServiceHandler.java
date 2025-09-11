package com.example.texnoera.service.concrete;

import com.example.texnoera.dao.entity.UserEntity;
import com.example.texnoera.dao.repository.UserRepository;
import com.example.texnoera.dto.request.CreateUserRequest;
import com.example.texnoera.dto.request.UpdateUserRequest;
import com.example.texnoera.dto.response.UserResponse;
import com.example.texnoera.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.texnoera.enums.Status.ACTIVE;
import static com.example.texnoera.enums.Status.DELETED;
import static com.example.texnoera.enums.Status.IN_PROGRESS;
import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserServiceHandler implements UserService {
    UserRepository userRepository;

    @Override
    public UserResponse saveUser(CreateUserRequest request) {

        UserEntity userEntity = UserEntity.builder()
                .firstName(request.getFirstName())
                .age(request.getAge())
                .status(ACTIVE)
                .build();

        userRepository.save(userEntity);

        return UserResponse.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .age(userEntity.getAge())
                .status(userEntity.getStatus())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .build();
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        UserEntity userEntity = fetchUserIfExist(id);

        if (request.getFirstName() != null && !request.getFirstName().trim().isEmpty()) {
            userEntity.setFirstName(request.getFirstName());
        }

        if (request.getAge() != null) {
            userEntity.setAge(request.getAge());
        }

        userEntity.setStatus(IN_PROGRESS);

        userRepository.save(userEntity);

        return UserResponse.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .age(userEntity.getAge())
                .status(userEntity.getStatus())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .build();
    }

    @Override
    public UserResponse findUserById(Long id) {
        UserEntity userEntity = fetchUserIfExist(id);

        return buildUserResponse(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity userEntity = fetchUserIfExist(id);

        userEntity.setStatus(DELETED);
        userRepository.save(userEntity);
    }

    @Override
    public List<UserResponse> findAllUsers() {
        return userRepository.findAll().stream()
                .filter(userEntity -> !userEntity.getStatus().equals(DELETED))
                .map(this::buildUserResponse)
                .collect(Collectors.toList());

//        List<UserResponse> responses = new ArrayList<>();
//
//        var users = userRepository.findAll();
//
//        for (UserEntity user : users) {
//            UserResponse response = buildUserResponse(user);
//            responses.add(response);
//        }
//
//        return responses;
    }

    private UserEntity fetchUserIfExist(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("User not found: " + id));
    }

    private UserResponse buildUserResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .age(userEntity.getAge())
                .status(userEntity.getStatus())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .build();
    }

}
