package com.nexign.service;

import com.nexign.entity.UserEntity;
import com.nexign.model.User;
import com.nexign.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getUserList() {
        System.out.println("Список пользователей: " + userRepository);
        return userRepository.findAll();
    }

    public void addUsers(UserEntity...users) {

        userRepository.saveAll(Arrays.asList(users));
        System.out.println("Все пользователи добавлены");
    }


    public Optional<UserEntity> login(String login, String pass) {
        if (login == null || pass == null) {
            throw new IllegalArgumentException("login or pass is null");
        }

        return userRepository.findAll().stream()
                .filter(user -> user.getLogin().equals(login))
                .filter(user -> user.getPassword().equals(pass))
                .findFirst();
    }



}
