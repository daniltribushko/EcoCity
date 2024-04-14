package com.example.EcoCity.exceptions.files;

import com.example.EcoCity.exceptions.GlobalAppException;

/**
 * @author Tribushko Danil
 * @since 13.04.2024
 */
public class FileStorageException extends GlobalAppException {
    public FileStorageException(String fileName) {
        super(400, "Failed to store file \" " + fileName + " \"");
    }
}
