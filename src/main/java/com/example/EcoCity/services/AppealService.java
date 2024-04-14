package com.example.EcoCity.services;

import com.example.EcoCity.models.dto.request.AppealRequest;
import com.example.EcoCity.models.dto.request.CreateAppealRequest;
import com.example.EcoCity.models.dto.response.AppealResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Tribushko Danil
 * @since 12.04.2024
 * <p>
 * Сервис для работы с обращениями
 */
public interface AppealService {
    AppealResponse create(String email, CreateAppealRequest request);
    AppealResponse update(String email, Long id, AppealRequest request);
    void delete(String email, Long id);
    AppealResponse findById(Long id);
    AppealResponse addAppealPhotos(String email, Long id, MultipartFile[] files);
    AppealResponse deleteFile(String email, Long id, String fileName);
    AppealResponse deleteAllFiles(String email, Long id);
    Resource getFile(String email, Long id, String fileName);
}
