package com.nexign.service;

import com.nexign.entity.UserEntity;
import com.nexign.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    void loginSuccessIfUserExist() {
        userService = new UserService(userRepository);
        UserEntity userEntity = new UserEntity(0L, "Alex", "pass");

        userService.addUsers(userEntity);

        when(userRepository.findAll()).thenReturn(List.of(userEntity));

        Optional<UserEntity> optionalUser = userService.login(userEntity.getLogin(), userEntity.getPassword());

        assertThat(optionalUser.isPresent()).isTrue();
        optionalUser.ifPresent(user -> assertThat(userEntity).isEqualTo(user));
    }

}