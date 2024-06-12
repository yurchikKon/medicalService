package com.blueTeam.medicalService.mapper.util;

import com.blueTeam.medicalService.mapper.exception.PasswordEncoderException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PasswordEncoderUtil {

    private final PasswordEncoder passwordEncoder;

    public String encode(String password) {
        return Optional.ofNullable(password)
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .orElseThrow(() -> new PasswordEncoderException("Could not encode password"));
    }
}
