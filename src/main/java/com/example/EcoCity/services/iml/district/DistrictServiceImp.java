package com.example.EcoCity.services.iml.district;

import com.example.EcoCity.aspects.annotations.CheckUserAdmin;
import com.example.EcoCity.exceptions.districts.DistrictAlreadyExistException;
import com.example.EcoCity.models.dto.request.DistrictRequest;
import com.example.EcoCity.models.dto.response.DistrictResponse;
import com.example.EcoCity.models.entities.District;
import com.example.EcoCity.services.districts.DBServiceDistrict;
import com.example.EcoCity.services.districts.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tribushko Danil
 * @since 31.03.2024
 * <p>
 * Реализация сервиса по работе с районами
 */
@Service
public class DistrictServiceImp implements DistrictService {
    private final DBServiceDistrict dbServiceDistrict;

    @Autowired
    public DistrictServiceImp(DBServiceDistrict dbServiceDistrict){
        this.dbServiceDistrict = dbServiceDistrict;
    }

    @Override
    @CheckUserAdmin
    public DistrictResponse create(String email, DistrictRequest request) {
        String name = request.getName();
        if (dbServiceDistrict.existByName(name)){
            throw new DistrictAlreadyExistException(name);
        }
        District district = new District(name);
        dbServiceDistrict.save(district);
        return DistrictResponse.mapFromEntity(district);
    }

    @Override
    public DistrictResponse findById(Integer id) {
        District district = dbServiceDistrict.findById(id);
        return DistrictResponse.mapFromEntity(district);
    }

    @Override
    @CheckUserAdmin
    public void delete(String email, Integer id) {
        District district = dbServiceDistrict.findById(id);
        dbServiceDistrict.delete(district);
    }

    @Override
    @CheckUserAdmin
    public DistrictResponse update(String email, Integer id, DistrictRequest request) {
        District district = dbServiceDistrict.findById(id);
        district.setName(request.getName());
        dbServiceDistrict.save(district);
        return DistrictResponse.mapFromEntity(district);
    }
}
