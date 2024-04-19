package com.example.EcoCity.repositories;

import com.example.EcoCity.models.entities.AppealPhoto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

/**
 * @author Tribushko Danil
 * @since 16.04.2024
 * <p>
 * Класс для тестирования репозитория по работе с изображениями обращений
 */
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestAppealPhotoRepository {
    private final AppealPhotoRepository appealPhotoRepository;

    @Autowired
    TestAppealPhotoRepository(AppealPhotoRepository appealPhotoRepository) {
        this.appealPhotoRepository = appealPhotoRepository;
    }

    @BeforeAll
    void addAppealPhotos() {
        AppealPhoto appealPhoto1 = new AppealPhoto("Appeal photo 1");
        AppealPhoto appealPhoto2 = new AppealPhoto("Appeal photo 2");
        AppealPhoto appealPhoto3 = new AppealPhoto("Appeal photo 3");

        appealPhotoRepository.saveAll(List.of(appealPhoto1, appealPhoto2, appealPhoto3));
    }

    @AfterAll
    void cleanDB() {
        appealPhotoRepository.deleteAll();
    }

    @Test
    @Order(1)
    void saveAppealPhotoTest() {
        Assertions.assertEquals(3, appealPhotoRepository.findAll().size());
    }

    @Test
    @Order(2)
    void findAppealPhotoByIdTest() {
        AppealPhoto appealPhoto1 = appealPhotoRepository.findById(1L).orElse(null);
        AppealPhoto appealPhoto2 = appealPhotoRepository.findById(2L).orElse(null);

        Assertions.assertNotNull(appealPhoto1);
        Assertions.assertNotNull(appealPhoto2);
        Assertions.assertEquals(1L, appealPhoto1.getId());
        Assertions.assertEquals(2L, appealPhoto2.getId());
    }

    @Test
    @Order(3)
    void updateAppealPhotoTest() {
        AppealPhoto appealPhoto = appealPhotoRepository.findById(3L).orElse(null);
        Assertions.assertNotNull(appealPhoto);

        appealPhoto.setPhotoUrl("Updated appeal photo");
        appealPhotoRepository.save(appealPhoto);
        appealPhoto = appealPhotoRepository.findById(3L).orElse(null);

        Assertions.assertNotNull(appealPhoto);
        Assertions.assertEquals("Updated appeal photo", appealPhoto.getPhotoUrl());
    }

    @Test
    @Order(4)
    void deleteAppealPhotoTest() {
        AppealPhoto appealPhoto = appealPhotoRepository.findById(2L).orElse(null);
        Assertions.assertNotNull(appealPhoto);

        appealPhotoRepository.delete(appealPhoto);

        Assertions.assertEquals(2, appealPhotoRepository.findAll().size());
    }
}
