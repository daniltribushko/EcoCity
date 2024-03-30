package com.example.EcoCity.exceptions.districts;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 */
public class DistrictByIdNotFoundException extends GlobalAppException {
    public DistrictByIdNotFoundException(Integer id) {
        super(404, "District with " + id + " not found");
    }
}
