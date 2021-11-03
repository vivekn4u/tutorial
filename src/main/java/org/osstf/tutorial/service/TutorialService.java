package org.osstf.tutorial.service;

import org.osstf.tutorial.model.Tutorial;

import java.util.List;
import java.util.Optional;

/**
 * Tutorial service interface
 */
public interface TutorialService {

    /**
     * @param tutorial
     * @return Tutorial
     */
    public abstract Tutorial createTutorial(Tutorial tutorial);

    /**
     * @param id
     * @param tutorial
     * @return
     */
    public abstract Optional<Tutorial> updateTutorial(long id, Tutorial tutorial);

    /**
     * @param id
     * @return true if deleted else false
     */
    public abstract boolean deleteTutorial(long id);

    /**
     * @return List of tutorials
     */
    public abstract List<Tutorial> getTutorials(String title, Integer page, Integer rows);

    /**
     * @param id
     * @return Optional tutorial as it may or may not contain not null value
     */
    public abstract Optional<Tutorial> getTutorial(long id);

    /**
     * @return List of tutorials
     */
    public abstract List<Tutorial> getPublishedTutorials(Integer page, Integer rows);

}
