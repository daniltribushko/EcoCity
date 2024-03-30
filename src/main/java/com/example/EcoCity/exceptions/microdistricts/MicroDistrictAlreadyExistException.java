package com.example.EcoCity.exceptions.microdistricts;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 */
public class MicroDistrictAlreadyExistException extends GlobalAppException {
    public MicroDistrictAlreadyExistException(String name) {
        super(409, "Micro district: " + name + " already exist");
    }
}
