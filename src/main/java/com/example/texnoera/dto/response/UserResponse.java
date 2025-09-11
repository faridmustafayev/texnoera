package com.example.texnoera.dto.response;

import com.example.texnoera.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class UserResponse {
    Long id;
    String firstName;
    Integer age;
    Status status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
