package com.example.EcoCity.exceptions.appeals;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 12.04.2024
 */
public class AppealByIdNotFoundException extends GlobalAppException {
    public AppealByIdNotFoundException(Long id) {
        super(404, "Appeal with " + id + " id not found exception");
    }
}
