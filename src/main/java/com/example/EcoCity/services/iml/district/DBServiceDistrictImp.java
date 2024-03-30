package com.example.EcoCity.services.iml.district;

import com.example.EcoCity.exceptions.districts.DistrictByIdNotFoundException;
import com.example.EcoCity.models.entities.District;
import com.example.EcoCity.repositories.DistrictRepository;
import com.example.EcoCity.services.districts.DBServiceDistrict;
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
        districtRepository.save(object);
    }

    @Override
    public void delete(District object) {
        districtRepository.delete(object);
    }

    @Override
    public boolean existByName(String name) {
        return districtRepository.existsByName(name);
    }
}
