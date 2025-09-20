package com.example.transaction.service.concrete;

import com.example.transaction.dao.entity.UserEntity;
import com.example.transaction.dao.repository.CardRepository;
import com.example.transaction.dao.repository.UserRepository;
import com.example.transaction.dto.request.UserRequest;
import com.example.transaction.service.abstraction.CardService;
import com.example.transaction.service.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE)
public class UserServiceHandler implements UserService {
    final UserRepository userRepository;
    final CardService cardService;
    final CardRepository cardRepository;
    final TransactionalUserService transactionalUserService;
    UserService userService;

//    @Lazy
//    @Autowired
//    public void setCardService(UserService userService) {
//        this.userService = userService;
//    }


//    @Transactional
    public void test(UserRequest request) {
//        UserEntity userEntity = buildUserEntity(request);
//        userRepository.save(userEntity);
//
//        CardRequest cardInfo = request.getCardInfo();
//
//        CardEntity cardEntity = CardEntity.builder()
//                .cvv(cardInfo.getCvv())
//                .cardNumber(cardInfo.getCardNumber())
//                .build();
//
//        cardRepository.save(cardEntity);


        transactionalUserService.createUser(request);

//        userService.createUser(request);
    }

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
