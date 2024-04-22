package com.example.EcoCity.services.iml;

import com.example.EcoCity.exceptions.users.PasswordNotConfirmedException;
import com.example.EcoCity.models.dto.request.SignInRequest;
import com.example.EcoCity.models.dto.request.SignUpRequest;
import com.example.EcoCity.models.dto.response.JwtTokenResponse;
import com.example.EcoCity.models.dto.response.UserResponse;
import com.example.EcoCity.models.entities.User;
import com.example.EcoCity.models.enums.RecordState;
import com.example.EcoCity.services.JwtTokenService;
import com.example.EcoCity.services.RoleService;
import com.example.EcoCity.services.AuthUserService;
import com.example.EcoCity.services.db.DBServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Реализация сервиса по авторизации и регистрации пользователя
 */
@Service
public class AuthUserServiceImp implements AuthUserService {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;
    private final DBServiceUser dbServiceUser;
    private final RoleService roleService;
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    public AuthUserServiceImp(PasswordEncoder passwordEncoder,
                              JwtTokenService jwtTokenService,
                              DBServiceUser dbServiceUser,
                              RoleService roleService,
                              AuthenticationProvider authenticationProvider){
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
        this.dbServiceUser = dbServiceUser;
        this.roleService = roleService;
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public UserResponse signUp(SignUpRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        String confirmPassword = request.getConfirmPassword();
        if (!Objects.equals(password, confirmPassword)){
            throw new PasswordNotConfirmedException();
        }
        User user = new User()
                .builder()
                .email(email)
                .surname(request.getSurname())
                .name(request.getName())
                .password(passwordEncoder.encode(password))
                .createDate(LocalDateTime.now())
                .recordState(RecordState.ACTIVE)
                .lastOnlineDate(LocalDateTime.now())
                .roles(Set.of(roleService.findByName("USER")))
                .build();
        dbServiceUser.save(user);
        return UserResponse.mapFromEntity(user);
    }

    @Override
    public JwtTokenResponse signIn(SignInRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        try {
            authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(email,
                    password));
        } catch (BadCredentialsException e){
            throw new BadCredentialsException("Wrong email or password");
        }
        User user = dbServiceUser.findByEmail(email);
        user.setLastOnlineDate(LocalDateTime.now());
        dbServiceUser.update(user);
        return new JwtTokenResponse(jwtTokenService.createToken(user));
    }
}
