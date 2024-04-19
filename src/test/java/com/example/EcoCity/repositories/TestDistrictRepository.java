package com.example.EcoCity.repositories;

import com.example.EcoCity.models.entities.District;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

/**
 * @author Tribushko Danil
 * @since 16.04.2024
 * <p>
 * Класс для тестирования репозитория по работе с районами
 */
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDistrictRepository {
    private final DistrictRepository districtRepository;

    @Autowired
    TestDistrictRepository(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @BeforeAll
    void addDistricts() {
        District district1 = new District("District 1");
        District district2 = new District("District 2");
        District district3 = new District("District 3");

        districtRepository.saveAll(List.of(district1, district2, district3));
    }

    @AfterAll
    void deleteDistricts() {
        districtRepository.deleteAll();
    }

    @Test
    @Order(1)
    void saveDistrictsTest() {
        Assertions.assertEquals(3, districtRepository.findAll().size());
    }

    @Test
    @Order(2)
    void findDistrictByIdTest() {
        District district = districtRepository.findById(1).orElse(null);

        Assertions.assertNotNull(district);
        Assertions.assertEquals(1, district.getId());

    }

    @Test
    @Order(3)
    void updateDistrictTest() {
        District district = districtRepository.findById(1).orElse(null);
        Assertions.assertNotNull(district);

        district.setName("Updated district 1");
        districtRepository.save(district);
        district = districtRepository.findById(1).orElse(null);

        Assertions.assertNotNull(district);
        Assertions.assertEquals("Updated district 1", district.getName());
    }

    @Test
    @Order(4)
    void existDistrictByNameTest() {
        Assertions.assertTrue(districtRepository.existsByName("District 2"));
    }

    @Test
    @Order(5)
    void deleteDistrict() {
        District district = districtRepository.findById(1).orElse(null);
        Assertions.assertNotNull(district);

        districtRepository.delete(district);

        Assertions.assertEquals(2, districtRepository.findAll().size());
    }
}
