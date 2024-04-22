package com.example.EcoCity.exceptions.users;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 22.04.2024
 */
public class WrongRecordStateException extends GlobalAppException {
    public WrongRecordStateException() {
        super(400, "Wrong record state");
    }
}
