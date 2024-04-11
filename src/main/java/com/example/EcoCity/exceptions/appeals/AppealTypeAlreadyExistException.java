package com.example.EcoCity.exceptions.appeals;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 01.04.2024
 */
public class AppealTypeAlreadyExistException extends GlobalAppException {
    public AppealTypeAlreadyExistException(String name) {
        super(409, "AppealType " + name + " already exist");
    }
}
