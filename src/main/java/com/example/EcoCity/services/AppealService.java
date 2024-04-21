package com.example.EcoCity.services;

import com.example.EcoCity.models.dto.request.AppealRejectCommentRequest;
import com.example.EcoCity.models.dto.request.AppealRequest;
import com.example.EcoCity.models.dto.request.CreateAppealRequest;
import com.example.EcoCity.models.dto.response.AppealResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Tribushko Danil
 * @since 12.04.2024
 * <p>
 * Сервис для работы с обращениями
 */
public interface AppealService {
    AppealResponse create(@Email
                          @Size(min = 7, message = "Email must be contain 7 characters")
                          String email,
                          @Valid
                          CreateAppealRequest request);

    AppealResponse update(@Email
                          @Size(min = 7, message = "Email must be contain 7 characters")
                          String email,
                          @Min(value = 1, message = "Id can not be less than 1")
                          Long id,
                          @Valid
                          AppealRequest request);

    void delete(@Email
                @Size(min = 7, message = "Email must be contain 7 characters")
                String email,
                @Min(value = 1, message = "Id can not be less than 1")
                Long id);

    AppealResponse findById(@Min(value = 1, message = "Id can not be less than 1")
                            Long id);

    AppealResponse addAppealPhotos(@Email
                                   @Size(min = 7, message = "Email must be contain 7 characters")
                                   String email,
                                   @Min(value = 1, message = "Id can not be less than 1")
                                   Long id,
                                   MultipartFile[] files);

    AppealResponse deleteFile(@Email
                              @Size(min = 7, message = "Email must be contain 7 characters")
                              String email,
                              @Min(value = 1, message = "Id can not be less than 1")
                              Long id,
                              @NotBlank(message = "fileName can not be blank")
                              String fileName);

    AppealResponse deleteAllFiles(@Email
                                  @Size(min = 7, message = "Email must be contain 7 characters")
                                  String email,
                                  @Min(value = 1, message = "Id can not be less than 1")
                                  Long id);

    Resource getFile(@Email
                     @Size(min = 7, message = "Email must be contain 7 characters")
                     String email,
                     @Min(value = 1, message = "Id can not be less than 1")
                     Long id,
                     @NotBlank(message = "fileName can not be blank")
                     String fileName);

    AppealResponse reject(@Email
                          @Size(min = 7, message = "Email must be contain 7 characters")
                          String email,
                          @Min(value = 1, message = "Id can not be less than 1")
                          Long id,
                          @Valid
                          AppealRejectCommentRequest request);

    AppealResponse accept(@Email
                          @Size(min = 7, message = "Email must be contain 7 characters")
                          String email,
                          @Min(value = 1, message = "Id can not be blank")
                          Long id);
}
