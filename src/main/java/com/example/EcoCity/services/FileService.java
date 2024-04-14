package com.example.EcoCity.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Tribushko Danil
 * @since 13.04.2024
 * <p>
 * Сервис для работы с файлами в приложении
 */
public interface FileService {
    void store(MultipartFile file, String path, String fileName);
    void delete(String path, String fileName);
    Resource getFile(String path, String fileName);
}
