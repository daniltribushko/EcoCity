package com.example.EcoCity.services.db.imp;

import com.example.EcoCity.exceptions.districts.DistrictAlreadyExistException;
import com.example.EcoCity.exceptions.districts.DistrictByIdNotFoundException;
import com.example.EcoCity.models.entities.District;
import com.example.EcoCity.repositories.DistrictRepository;
import com.example.EcoCity.services.db.DBServiceDistrict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 *
 * Реализация сервиса по работе с районами по бд
 */
@Service
public class DBServiceDistrictImp implements DBServiceDistrict {
    private final DistrictRepository districtRepository;

    @Autowired
    public DBServiceDistrictImp(DistrictRepository districtRepository){
        this.districtRepository = districtRepository;
    }

    @Override
    public District findById(Integer id) {
        return districtRepository.findById(id)
                .orElseThrow(() -> new DistrictByIdNotFoundException(id));
    }

    @Override
    public void save(District object) {
        String name = object.getName();
        if (districtRepository.existsByName(name)){
            throw new DistrictAlreadyExistException(name);
        }
        districtRepository.save(object);
    }

    @Override
    public void delete(Integer id) {
        District district = findById(id);
        districtRepository.delete(district);
    }

    @Override
    public void update(District object) {
        districtRepository.save(object);
    }
}
