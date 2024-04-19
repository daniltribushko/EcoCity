package com.example.EcoCity.services.imp;

import com.example.EcoCity.exceptions.users.PasswordNotConfirmedException;
import com.example.EcoCity.models.dto.request.SignUpRequest;
import com.example.EcoCity.models.dto.response.UserResponse;
import com.example.EcoCity.models.entities.Role;
import com.example.EcoCity.services.RoleService;
import com.example.EcoCity.services.db.DBServiceUser;
import com.example.EcoCity.services.iml.AuthUserServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Tribushko Danil
 * @since 18.04.2024
 * <p>
 * Класс для тестирования сервиса по авторизации и регистрации пользователей
 */
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock
    private RoleService roleService;
    @Mock
    private DBServiceUser dbServiceUser;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private AuthUserServiceImp authUserService;

    @Test
    void signUpPasswordsNotConfirm() {
        SignUpRequest request = new SignUpRequest(null,
                null,
                null,
                "password 1",
                "password 2");
        Class<PasswordNotConfirmedException> exceptionClass = PasswordNotConfirmedException.class;
        PasswordNotConfirmedException exception = Assertions.assertThrows(exceptionClass,
                () -> authUserService.signUp(request));

        Assertions.assertEquals(exceptionClass, exception.getClass());
    }

    @Test
    void signUpUserTest() {
        SignUpRequest request = new SignUpRequest("test",
                null,
                null,
                "password 1",
                "password 1");
        Role role = new Role("test role");
        Mockito.when(roleService.findByName("USER"))
                .thenReturn(role);
        UserResponse expected = new UserResponse()
                .builder()
                .id(1L)
                .email("test")
                .build();
        UserResponse actual = authUserService.signUp(request);
        Assertions.assertEquals(expected.getEmail(), actual.getEmail());
    }
}
