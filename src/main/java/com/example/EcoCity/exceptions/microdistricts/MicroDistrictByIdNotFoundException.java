package com.example.EcoCity.exceptions.microdistricts;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 */
public class MicroDistrictByIdNotFoundException extends GlobalAppException {
    public MicroDistrictByIdNotFoundException(Integer id) {
        super(404, "Micro district with " + id + " not found");
    }
}
