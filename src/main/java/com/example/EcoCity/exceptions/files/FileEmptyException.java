package com.example.EcoCity.exceptions.files;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 13.04.2024
 */
public class FileEmptyException extends GlobalAppException {
    public FileEmptyException(String fileName) {
        super(400, "File: " + fileName + " is empty");
    }
}
