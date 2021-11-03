package org.osstf.tutorial.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.osstf.tutorial.model.Tutorial;

import static org.junit.jupiter.api.Assertions.*;

class TutorialServiceImplTest {

    private TutorialServiceImpl service;
    private Tutorial tutorial;

    @BeforeEach
    void setUp() {
        service = new TutorialServiceImpl();
        tutorial = new Tutorial();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createTutorial() {
    }

    @Test
    void updateTutorial() {
    }

    @Test
    void deleteTutorial() {
    }

    @Test
    void getTutorials() {
    }

    @Test
    void getTutorial() {
        //Assertions.assertThat(service.getTutorial(2)).isNotNull();
        Assertions.assertThat(service.getTutorial(2)).isEqualTo(null);
    }

    @Test
    void getPublishedTutorials() {
    }
}