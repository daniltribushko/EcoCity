package com.example.EcoCity.services.iml;

import com.example.EcoCity.exceptions.files.FileEmptyException;
import com.example.EcoCity.exceptions.files.FileStorageException;
import com.example.EcoCity.services.FileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
                Path filePath = Path.of(getPath(path,fileName) + ".jpeg");
                Files.copy(stream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException exception) {
            throw new FileStorageException(fileName);
        }
    }

    @Override
    public void delete(String path, String fileName) {
        try {
            Files.delete(Path.of(getPath(path, fileName)));
        } catch (FileNotFoundException exception){
            throw new com.example.EcoCity.exceptions.files.FileNotFoundException(fileName);
        } catch (IOException e) {
            throw new FileStorageException(fileName);
        }
    }

    @Override
    public Resource getFile(String path, String fileName) {
        try {
            Path file = Path.of(getPath(path,fileName));
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()){
                return resource;
            } else {
                throw new com.example.EcoCity.exceptions.files.FileNotFoundException(fileName);
            }
        } catch (MalformedURLException e) {
            throw new FileStorageException(fileName);
        }
    }

    private String getPath(String path, String fileName){
        return FILES_URL + "/" + path + "/" + fileName;
    }
}
