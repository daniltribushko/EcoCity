package com.example.EcoCity.exceptions.files;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 13.04.2024
 */
public class FileNotFoundException extends GlobalAppException {
    public FileNotFoundException(String fileName) {
        super(404, "File: " + fileName + " not found");
    }
}
