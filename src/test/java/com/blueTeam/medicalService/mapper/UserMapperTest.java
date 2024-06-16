package com.blueTeam.medicalService.mapper;

import com.blueTeam.medicalService.config.MapperTestRunner;
import com.blueTeam.medicalService.dto.user.UserCreateEditDto;
import com.blueTeam.medicalService.dto.user.UserRepresentationDto;
import com.blueTeam.medicalService.entities.User;
import com.blueTeam.medicalService.mapper.util.PasswordEncoderUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static com.blueTeam.medicalService.entities.enums.Role.MANAGER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = MapperTestRunner.class)
public class UserMapperTest {

    @Mock
    private PasswordEncoderUtil passwordEncoder;

    @InjectMocks
    private final UserMapper userMapper;

    private static User user;
    private static UserRepresentationDto representationDto;
    private static UserCreateEditDto createEditDto;

    @BeforeAll
    static void setUp() {
        user = new User(1L, "firstName", "lastName", MANAGER, "+375(29)111-11-11",
                "testAddress", "testLogin", "testPassword", "test@mail.com");

        representationDto = UserRepresentationDto.builder()
                .id(1L)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(MANAGER)
                .address(user.getAddress())
                .login(user.getLogin())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();

        createEditDto = UserCreateEditDto.builder()
                .id(1L)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(MANAGER)
                .address(user.getAddress())
                .login(user.getLogin())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .build();
    }

    @AfterEach
    void cleanChanges() {
        user.setId(1L);
    }

    @Test
    void mapToUserDto_whenGivenUser_thenReturnUserRepresentationDto() {
        UserRepresentationDto actualUser = userMapper.mapToUserDto(user);

        assertThat(actualUser).isEqualTo(representationDto);
    }

    @Test
    void mapToUser_whenGivenRepresentationDto_thenReturnUser() {
        User expectedUser = user;

        User actualUser = userMapper.mapToUser(representationDto);
        expectedUser.setPassword(null);

        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void mapToUser_whenGivenCreateEditDto_thenReturnUser() {
        var encodedPassword = "encoded_password";
        User expectedUser = user;

        when(passwordEncoder.encode(anyString())).thenReturn(encodedPassword);

        User actualUser = userMapper.mapToUser(createEditDto);
        expectedUser.setPassword(encodedPassword);
        expectedUser.setId(null);

        assertThat(actualUser).isEqualTo(expectedUser);
    }
}
