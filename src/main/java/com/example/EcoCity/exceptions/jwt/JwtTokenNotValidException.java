package com.example.EcoCity.exceptions.jwt;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 */
public class JwtTokenNotValidException extends GlobalAppException {
    public JwtTokenNotValidException() {
        super(401, "Token is not valid");
    }
}
