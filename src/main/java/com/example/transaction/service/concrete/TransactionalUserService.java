package com.example.transaction.service.concrete;

import com.example.transaction.dao.entity.UserEntity;
import com.example.transaction.dao.repository.UserRepository;
import com.example.transaction.dto.request.UserRequest;
import com.example.transaction.service.abstraction.CardService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TransactionalUserService {
    UserRepository userRepository;
    CardService cardService;

    @Transactional(rollbackFor = Exception.class, noRollbackFor = ArithmeticException.class)
    public void createUser(UserRequest request) {
        UserEntity userEntity = buildUserEntity(request);
        userRepository.save(userEntity);
        cardService.createCard(request.getCardInfo());
    }

    private UserEntity buildUserEntity(UserRequest request) {
        return UserEntity.builder()
                .firstName(request.getFirstName())
                .age(request.getAge())
                .build();
    }

}
