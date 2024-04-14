package com.example.EcoCity.services.iml;

import com.example.EcoCity.aspects.annotations.CheckUserAppealAuthor;
import com.example.EcoCity.models.dto.request.AppealRequest;
import com.example.EcoCity.models.dto.request.CreateAppealRequest;
import com.example.EcoCity.models.dto.response.AppealResponse;
import com.example.EcoCity.models.entities.*;
import com.example.EcoCity.models.enums.AppealStatus;
import com.example.EcoCity.services.AppealService;
import com.example.EcoCity.services.FileService;
import com.example.EcoCity.services.db.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;


/**
 * @author Tribushko Danil
 * @since 12.04.2024
 * <p>
 * Реализация сервиса по работе с обращенями
 */
@Service
public class AppealServiceImp implements AppealService {
    private final DBServiceAppeal dbServiceAppeal;
    private final DBServiceUser dbServiceUser;
    private final DBServiceDistrict dbServiceDistrict;
    private final DBServiceMicroDistrict dbServiceMicroDistrict;
    private final DBServiceAppealType dbServiceAppealType;
    private final FileService fileService;

    @Value("${appeal.photo.path}")
    private String appealPhotoUrl;

    @Autowired
    public AppealServiceImp(DBServiceAppeal dbServiceAppeal,
                            DBServiceUser dbServiceUser,
                            DBServiceDistrict dbServiceDistrict,
                            DBServiceMicroDistrict dbServiceMicroDistrict,
                            DBServiceAppealType dbServiceAppealType,
                            FileService fileService){
        this.dbServiceAppeal = dbServiceAppeal;
        this.dbServiceUser = dbServiceUser;
        this.dbServiceDistrict = dbServiceDistrict;
        this.dbServiceMicroDistrict = dbServiceMicroDistrict;
        this.dbServiceAppealType = dbServiceAppealType;
        this.fileService = fileService;
    }

    @Override
    public AppealResponse create(String email, CreateAppealRequest request) {
        User user = dbServiceUser.findByEmail(email);
        Integer districtId = request.getDistrictId();
        Integer microDistrictId = request.getMicroDistrictId();
        Integer appealTypeId = request.getAppealTypeId();
        District district = dbServiceDistrict.findById(districtId);
        MicroDistrict microDistrict = dbServiceMicroDistrict.findById(microDistrictId);
        AppealType appealType = dbServiceAppealType.findById(appealTypeId);
        Appeal appeal = new Appeal()
                .builder()
                .text(request.getText())
                .author(user)
                .address(request.getAddress())
                .district(district)
                .microDistrict(microDistrict)
                .type(appealType)
                .status(AppealStatus.WAITING)
                .build();
        dbServiceAppeal.save(appeal);
        return AppealResponse.mapFromEntity(appeal);
    }

    @Override
    @CheckUserAppealAuthor
    public AppealResponse update(String email, Long id, AppealRequest request) {
        Appeal appeal = dbServiceAppeal.findById(id);
        String text = request.getText();
        String address = request.getAddress();
        if (text != null){
            appeal.setText(text);
        }
        if (address != null){
            appeal.setAddress(address);
        }
        dbServiceAppeal.save(appeal);
        return AppealResponse.mapFromEntity(appeal);
    }

    @Override
    @CheckUserAppealAuthor
    public void delete(String email, Long id) {
        dbServiceAppeal.delete(id);
    }

    @Override
    public AppealResponse findById(Long id) {
        Appeal appeal = dbServiceAppeal.findById(id);
        return AppealResponse.mapFromEntity(appeal);
    }

    @Override
    @CheckUserAppealAuthor
    public AppealResponse addAppealPhotos(String email, Long id, MultipartFile[] files) {
        Appeal appeal = dbServiceAppeal.findById(id);
        String fileName = email + "_appeal_";
        Set<AppealPhoto> appealsPhoto = new HashSet<>();
        MultipartFile file;
        AppealPhoto appealPhoto;
        for (int i = 0; i < files.length; i++){
            file = files[i];
            String appealPhotoFileName = fileName + (i+1);
            fileService.store(file, appealPhotoUrl, appealPhotoFileName);
            appealPhoto = new AppealPhoto(appealPhotoFileName);
            appealPhoto.setAppeal(appeal);
            appealsPhoto.add(appealPhoto);
        }
        appeal.setPhotos(appealsPhoto);
        dbServiceAppeal.save(appeal);
        return AppealResponse.mapFromEntity(appeal);
    }
}
