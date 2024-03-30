package com.example.EcoCity.exceptions.districts;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 */
public class DistrictAlreadyExistException extends GlobalAppException {
    public DistrictAlreadyExistException(String name) {
        super(409, "District: " + name + "already exist");
    }
}
