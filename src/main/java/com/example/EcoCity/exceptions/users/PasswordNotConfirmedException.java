package com.example.EcoCity.exceptions.users;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 */
public class PasswordNotConfirmedException extends GlobalAppException {
    public PasswordNotConfirmedException() {
        super(400, "Password mismatch");
    }
}
