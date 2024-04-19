package com.example.EcoCity.repositories;

import com.example.EcoCity.models.entities.AppealType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

/**
 * @author Tribushko Danil
 * @since 15.04.2024
 * <p>
 * Класс для тестирования репозитория по работе с типами обращений
 */
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestAppealTypeRepository {
    private final AppealTypeRepository appealTypeRepository;

    @Autowired
    TestAppealTypeRepository(AppealTypeRepository appealTypeRepository) {
        this.appealTypeRepository = appealTypeRepository;
    }

    @BeforeAll
    void addAppealType() {
        AppealType appealType1 = new AppealType("Appeal type 1");
        AppealType appealType2 = new AppealType("Appeal type 2");
        AppealType appealType3 = new AppealType("Appeal type 3");

        appealTypeRepository.saveAll(List.of(appealType1, appealType2, appealType3));
    }

    @AfterAll
    void cleanBD() {
        appealTypeRepository.deleteAll();
    }

    @Test
    @Order(1)
    void saveAppealTypeTest() {
        Assertions.assertEquals(3, appealTypeRepository.findAll().size());
    }

    @Test
    @Order(2)
    void findAppealTypeByIdTest() {
        AppealType appealType2 = appealTypeRepository.findById(2).orElse(null);
        AppealType appealType3 = appealTypeRepository.findById(3).orElse(null);

        Assertions.assertNotNull(appealType2);
        Assertions.assertNotNull(appealType3);
        Assertions.assertEquals(2, appealType2.getId());
        Assertions.assertEquals(3, appealType3.getId());
    }

    @Test
    @Order(3)
    void updateAppealTypeTest() {
        AppealType appealType1 = appealTypeRepository.findById(1).orElse(null);
        Assertions.assertNotNull(appealType1);

        appealType1.setName("Updated appeal type");
        appealTypeRepository.save(appealType1);
        appealType1 = appealTypeRepository.findById(1).orElse(null);

        Assertions.assertNotNull(appealType1);
        Assertions.assertEquals("Updated appeal type", appealType1.getName());
    }

    @Test
    @Order(4)
    void existAppealTypeByNameTest() {
        Assertions.assertTrue(appealTypeRepository.existsByName("Appeal type 2"));
    }

    @Test
    @Order(5)
    void deleteAppealTypeTest() {
        AppealType appealType2 = appealTypeRepository.findById(2).orElse(null);
        Assertions.assertNotNull(appealType2);

        appealTypeRepository.delete(appealType2);

        Assertions.assertEquals(2, appealTypeRepository.findAll().size());
    }
}
