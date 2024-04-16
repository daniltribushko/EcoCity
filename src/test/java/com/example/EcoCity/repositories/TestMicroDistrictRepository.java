package com.example.EcoCity.repositories;

import com.example.EcoCity.models.entities.MicroDistrict;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

/**
 * @author Tribushko Danil
 * @since 15.04.2024
 * <p>
 * Класс для тестирования репозитория по работе с микрорайонами
 */
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestMicroDistrictRepository {
    private final MicroDistrictRepository microDistrictRepository;

    @Autowired
    TestMicroDistrictRepository(MicroDistrictRepository microDistrictRepository) {
        this.microDistrictRepository = microDistrictRepository;
    }

    @BeforeAll
    void addMicroDistricts() {
        MicroDistrict microDistrict1 = new MicroDistrict("Micro district 1", null);
        MicroDistrict microDistrict2 = new MicroDistrict("Micro district 2", null);
        MicroDistrict microDistrict3 = new MicroDistrict("Micro district 3", null);

        microDistrictRepository.saveAll(List.of(microDistrict1, microDistrict2, microDistrict3));
    }

    @AfterAll
    void cleanBD() {
        microDistrictRepository.deleteAll();
    }

    @Test
    @Order(1)
    void saveMicroDistrictTest() {
        Assertions.assertEquals(3, microDistrictRepository.findAll().size());
    }

    @Test
    @Order(2)
    void findMicroDistrictByIdTest() {
        MicroDistrict microDistrict = microDistrictRepository.findById(1).orElse(null);

        Assertions.assertNotNull(microDistrict);
        Assertions.assertEquals(1, microDistrict.getId());
    }

    @Test
    @Order(3)
    void updateMicroDistrictTest() {
        MicroDistrict microDistrict = microDistrictRepository.findById(1).orElse(null);
        Assertions.assertNotNull(microDistrict);

        microDistrict.setName("Updated micro district");
        microDistrictRepository.save(microDistrict);
        microDistrict = microDistrictRepository.findById(1).orElse(null);

        Assertions.assertNotNull(microDistrict);
        Assertions.assertEquals("Updated micro district", microDistrict.getName());
    }

    @Test
    @Order(4)
    void existByNameMicroDistrictTest() {
        Assertions.assertTrue(microDistrictRepository.existsByName("Micro district 2"));
    }

    @Test
    @Order(5)
    void deleteMicroDistrictTest() {
        MicroDistrict microDistrict = microDistrictRepository.findById(1).orElse(null);
        Assertions.assertNotNull(microDistrict);

        microDistrictRepository.delete(microDistrict);

        Assertions.assertEquals(2, microDistrictRepository.findAll().size());
    }
}
