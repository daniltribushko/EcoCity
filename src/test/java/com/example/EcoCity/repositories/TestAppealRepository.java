package com.example.EcoCity.repositories;

import com.example.EcoCity.models.entities.Appeal;
import com.example.EcoCity.models.enums.AppealStatus;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

/**
 * @author Tribushko Danil
 * @since 15.04.2024
 * <p>
 * Класс для тестирования репозитория для работы с обращениями
 */
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestAppealRepository {
    private final AppealRepository appealRepository;

    @Autowired
    TestAppealRepository(AppealRepository appealRepository) {
        this.appealRepository = appealRepository;
    }

    @BeforeAll
    void addAppeals() {
        Appeal appeal1 = new Appeal()
                .builder()
                .address("Address 1")
                .status(AppealStatus.WAITING)
                .text("Test text 1")
                .build();

        Appeal appeal2 = new Appeal()
                .builder()
                .address("Address 2")
                .status(AppealStatus.ACCEPTED)
                .text("Test text 2")
                .build();

        Appeal appeal3 = new Appeal()
                .builder()
                .address("Address 3")
                .status(AppealStatus.REJECTED)
                .text("Test text 3")
                .build();

        appealRepository.saveAll(List.of(appeal1, appeal2, appeal3));
    }

    @AfterAll
    void cleanBD() {
        appealRepository.deleteAll();
    }

    @Test
    @Order(1)
    void saveAppealsTest() {
        Assertions.assertEquals(3, appealRepository.findAll().size());
    }


    @Test
    @Order(2)
    void findAppealByIdTest() {
        Appeal appeal1 = appealRepository.findById(1L).orElse(null);
        Appeal appeal3 = appealRepository.findById(3L).orElse(null);

        Assertions.assertNotNull(appeal1);
        Assertions.assertNotNull(appeal3);
        Assertions.assertEquals(1, appeal1.getId());
        Assertions.assertEquals(3, appeal3.getId());
    }

    @Test
    @Order(3)
    void updateAppealTest() {
        Appeal appeal = appealRepository.findById(2L).orElse(null);
        Assertions.assertNotNull(appeal);

        appeal.setText("Updated text");
        appealRepository.save(appeal);
        appeal = appealRepository.findById(2L).orElse(null);

        Assertions.assertNotNull(appeal);
        Assertions.assertEquals("Updated text", appeal.getText());
    }

    @Test
    @Order(4)
    void deleteAppeal() {
        Appeal appeal = appealRepository.findById(2L).orElse(null);
        Assertions.assertNotNull(appeal);

        appealRepository.delete(appeal);

        Assertions.assertEquals(2, appealRepository.findAll().size());
    }
}
