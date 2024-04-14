package com.example.EcoCity.services.iml;

import com.example.EcoCity.exceptions.files.FileEmptyException;
import com.example.EcoCity.exceptions.files.FileStorageException;
import com.example.EcoCity.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author Tribushko Danil
 * @since 13.04.2024
 * <p>
 * Реализация сервиса по работе с файлами
 */
@Service
public class FilesServiceImp implements FileService {
    public static final String FILES_URL = "src/main/resources/files";

    @Override
    public void store(MultipartFile file, String path, String fileName) {
        try {
            if (file.isEmpty()) {
                throw new FileEmptyException(fileName);
            }
            try (InputStream stream = file.getInputStream()) {
                Path filePath = Path.of(FILES_URL + "/" + path + "/" + fileName + ".jpeg");
                Files.copy(stream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException exception) {
            throw new FileStorageException(fileName);
        }
    }
}
