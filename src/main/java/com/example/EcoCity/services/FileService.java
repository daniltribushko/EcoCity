package com.example.EcoCity.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Tribushko Danil
 * @since 13.04.2024
 * <p>
 * Сервис для работы с файлами в приложении
 */
public interface FileService {
    void store(MultipartFile file, String path, String fileName);
}
