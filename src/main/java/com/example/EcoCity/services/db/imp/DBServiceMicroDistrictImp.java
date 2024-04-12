package com.example.EcoCity.services.db.imp;

import com.example.EcoCity.exceptions.microdistricts.MicroDistrictAlreadyExistException;
import com.example.EcoCity.exceptions.microdistricts.MicroDistrictByIdNotFoundException;
import com.example.EcoCity.models.entities.MicroDistrict;
import com.example.EcoCity.repositories.MicroDistrictRepository;
import com.example.EcoCity.services.db.DBServiceMicroDistrict;
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
        String name = object.getName();
        if (microDistrictRepository.existsByName(name)){
            throw new MicroDistrictAlreadyExistException(name);
        }
        microDistrictRepository.save(object);
    }

    @Override
    public void delete(Integer id) {
        MicroDistrict microDistrict = findById(id);
        microDistrictRepository.delete(microDistrict);
    }

    @Override
    public void update(MicroDistrict object) {
        microDistrictRepository.save(object);
    }
}
