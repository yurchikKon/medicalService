package com.paymentApplication.service.impl;

import com.paymentApplication.dto.UserDto;
import com.paymentApplication.repository.UserRepository;
import com.paymentApplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto findById(Long aLong) {
        return null;
    }

    @Override
    public UserDto create(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto update(UserDto userDto) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public UserDto getUserByName(String name) {
        return null;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return null;
    }
}
