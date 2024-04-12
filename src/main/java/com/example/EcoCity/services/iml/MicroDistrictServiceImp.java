package com.example.EcoCity.services.iml;

import com.example.EcoCity.aspects.annotations.CheckUserAdmin;
import com.example.EcoCity.exceptions.microdistricts.MicroDistrictAlreadyExistException;
import com.example.EcoCity.models.dto.request.CreateMicroDistrictRequest;
import com.example.EcoCity.models.dto.request.MicroDistrictRequest;
import com.example.EcoCity.models.dto.response.DistrictResponse;
import com.example.EcoCity.models.dto.response.MicroDistrictResponse;
import com.example.EcoCity.models.entities.District;
import com.example.EcoCity.models.entities.MicroDistrict;
import com.example.EcoCity.services.db.DBServiceDistrict;
import com.example.EcoCity.services.db.DBServiceMicroDistrict;
import com.example.EcoCity.services.MicroDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Tribushko Danil
 * @since 31.03.2024
 * <p>
 * Реализация сервиса по работе с микрорайонами
 */
@Service
public class MicroDistrictServiceImp implements MicroDistrictService {
    private final DBServiceMicroDistrict dbServiceMicroDistrict;
    private final DBServiceDistrict dbServiceDistrict;

    @Autowired
    public MicroDistrictServiceImp(DBServiceMicroDistrict dbServiceMicroDistrict,
                                   DBServiceDistrict dbServiceDistrict){
        this.dbServiceMicroDistrict = dbServiceMicroDistrict;
        this.dbServiceDistrict = dbServiceDistrict;
    }

    @Override
    @CheckUserAdmin
    public DistrictResponse create(String email,
                                   CreateMicroDistrictRequest request) {
        String name = request.getName();
        if (dbServiceMicroDistrict.existByName(name)) {
            throw new MicroDistrictAlreadyExistException(name);
        }
        Integer districtId = request.getDistrictId();
        District district = dbServiceDistrict.findById(districtId);
        MicroDistrict microDistrict = new MicroDistrict(name, district);
        Set<MicroDistrict> microDistricts = district.getMicroDistricts();
        microDistricts.add(microDistrict);
        dbServiceMicroDistrict.save(microDistrict);
        return new DistrictResponse(districtId,
                district.getName(),
                Set.of(MicroDistrictResponse.mapFromEntity(microDistrict)));
    }

    @Override
    @CheckUserAdmin
    public MicroDistrictResponse update(String email,
                                        Integer id,
                                        MicroDistrictRequest request) {
        MicroDistrict microDistrict = dbServiceMicroDistrict.findById(id);
        microDistrict.setName(request.getName());
        dbServiceMicroDistrict.save(microDistrict);
        return MicroDistrictResponse.mapFromEntity(microDistrict);
    }

    @Override
    public MicroDistrictResponse findById(Integer id) {
        return MicroDistrictResponse.mapFromEntity(dbServiceMicroDistrict.findById(id));
    }

    @Override
    @CheckUserAdmin
    public void delete(String email,
                       Integer id) {
        MicroDistrict microDistrict = dbServiceMicroDistrict.findById(id);
        dbServiceMicroDistrict.delete(microDistrict);
    }

    @Override
    @CheckUserAdmin
    public DistrictResponse changeDistrict(String email,
                                           Integer id,
                                           Integer newDistrictId) {
        MicroDistrict microDistrict = dbServiceMicroDistrict.findById(id);

        District oldDistrict = microDistrict.getDistrict();
        Set<MicroDistrict> microDistrictsFromOldDistrict = oldDistrict.getMicroDistricts();
        microDistrictsFromOldDistrict.remove(microDistrict);

        District newDistrict = dbServiceDistrict.findById(newDistrictId);
        Set<MicroDistrict> microDistrictsFromNewDistrict = newDistrict.getMicroDistricts();
        microDistrict.setDistrict(newDistrict);
        microDistrictsFromNewDistrict.add(microDistrict);

        dbServiceMicroDistrict.save(microDistrict);

        return new DistrictResponse(newDistrict.getId(),
                newDistrict.getName(),
                Set.of(MicroDistrictResponse.mapFromEntity(microDistrict)));
    }
}
