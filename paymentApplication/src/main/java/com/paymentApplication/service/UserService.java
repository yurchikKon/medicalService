package com.paymentApplication.service;

import com.paymentApplication.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends BaseService<UserDto, Long> {

    UserDto getUserByName(String name);

    UserDto getUserByEmail(String email);
}
