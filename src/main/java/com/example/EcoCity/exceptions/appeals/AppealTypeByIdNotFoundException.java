package com.example.EcoCity.exceptions.appeals;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 01.04.2024
 */
public class AppealTypeByIdNotFoundException extends GlobalAppException {
    public AppealTypeByIdNotFoundException(Integer id) {
        super(404, "AppealType with " + id + " id not found");
    }
}
