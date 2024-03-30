package com.example.EcoCity.services.iml.microdistricts;

import com.example.EcoCity.exceptions.microdistricts.MicroDistrictByIdNotFoundException;
import com.example.EcoCity.models.entities.MicroDistrict;
import com.example.EcoCity.repositories.MicroDistrictRepository;
import com.example.EcoCity.services.microdistricts.DBServiceMicroDistrict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tribushko Danil
 * @since 30.03.2024
 * <p>
 * Реализация интерфейса по работе с микрорайонами в бд
 */
@Service
public class DBServiceMicroDistrictImp implements DBServiceMicroDistrict {
    private final MicroDistrictRepository microDistrictRepository;

    @Autowired
    public DBServiceMicroDistrictImp(MicroDistrictRepository microDistrictRepository){
        this.microDistrictRepository = microDistrictRepository;
    }

    @Override
    public MicroDistrict findById(Integer id) {
        return microDistrictRepository.findById(id)
                .orElseThrow(() -> new MicroDistrictByIdNotFoundException(id));
    }

    @Override
    public void save(MicroDistrict object) {
        microDistrictRepository.save(object);
    }

    @Override
    public void delete(MicroDistrict object) {
        microDistrictRepository.delete(object);
    }

    @Override
    public boolean existByName(String name) {
        return microDistrictRepository.existsByName(name);
    }
}
