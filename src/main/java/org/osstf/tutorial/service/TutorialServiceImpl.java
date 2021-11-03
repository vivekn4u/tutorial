package org.osstf.tutorial.service;

import org.osstf.tutorial.model.Tutorial;
import org.osstf.tutorial.repository.TutorialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This is Tutorials Service class
 */
@Service
public class TutorialServiceImpl implements TutorialService {

    Logger logger = LoggerFactory.getLogger(TutorialServiceImpl.class);

    /**
     * Auto wire tutorials repository
     */
    @Autowired
    TutorialRepository repository;


    /**
     * @param tutorial
     * @return Tutorial
     */
    @Override
    public Tutorial createTutorial(Tutorial tutorial) {
        logger.info("[createTutorial] - started");
        try {
            Tutorial tutorialData = repository.
                    save(new Tutorial(tutorial.getTitle(),
                            tutorial.getDescription(),
                            tutorial.isPublished())
                    );
            logger.info("[createTutorial] - returned");
            return tutorialData;
        } catch (Exception e) {
            logger.error("[createTutorial] - " + e.getMessage());
            throw new RuntimeException("Error occurred while saving the data");
        }
    }

    /**
     * @param id
     * @param tutorial
     * @return Optional tutorial object for not-null check
     */
    @Override
    public Optional<Tutorial> updateTutorial(long id, Tutorial tutorial) {
        logger.info("[updateTutorial] - started");
        Optional<Tutorial> tutorialData = repository.findById(id);

        if (tutorialData.isPresent()) {
            Tutorial _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            logger.info("[updateTutorial] - returned");
            return Optional.of(repository.save(_tutorial));
        } else {
            //return empty Optional object
            logger.error("[updateTutorial] - return empty data");
            return tutorialData;
        }
    }

    /**
     * @param id
     * @return boolean status
     */
    @Override
    public boolean deleteTutorial(long id) {
        logger.info("[deleteTutorial] - started");
        try {
            repository.deleteById(id);
            logger.info("[deleteTutorial] - returned");
            return true;
        } catch (Exception e) {
            logger.error("[deleteTutorial] - " + e.getMessage());
            return false;
        }
    }

    /**
     * @return List of tutorials
     */
    @Override
    public List<Tutorial> getTutorials(String title, Integer page, Integer rows) {
        logger.info("[getTutorials] - started");
        List<Tutorial> tutorials = new ArrayList<Tutorial>();
        /*
         * Check if the title is empty then add all records in list else
         * find the title and add to list
         * similarly check for pagination else return all objects list
         * */
        if (title != null) {
            logger.info("[getTutorials] - get tutorials by title");
            repository.findByTitleContaining(title).forEach(tutorials::add);
        } else if (page != null && rows != null) {
            logger.debug("[getTutorials] - get tutorials by pagination [pages: " + page + ", rows:" + rows + "]");
            repository.findAll(PageRequest.of(page, rows)).forEach(tutorials::add);
        } else {
            logger.info("[getTutorials] - get all tutorials");
            repository.findAll().forEach(tutorials::add);
        }

        /*
         * if List is empty throw an exception
         * */
        if (tutorials.isEmpty()) {
            logger.error("[getTutorials] - Tutorial list is empty");
            throw new NullPointerException("Error occurred while getting tutorials");
        }

        logger.info("[getTutorials] - return list of tutorials");
        return tutorials;

    }

    /**
     * @param id
     * @return Optional tutorial object for not-null check
     */
    @Override
    public Optional<Tutorial> getTutorial(long id) {
        logger.info("[getTutorial] - started");
        Optional<Tutorial> tutorial = repository.findById(id);
        logger.info("[getTutorial] - return tutorial");
        return tutorial;
    }

    /**
     * @return List of Tutorials
     */
    @Override
    public List<Tutorial> getPublishedTutorials(Integer page, Integer rows) {
        logger.info("[getPublishedTutorials] - started");
        List<Tutorial> tutorials = new ArrayList<Tutorial>();

        /*
         * check for pagination else return all objects
         * */
        if (page != null && rows != null) {
            logger.debug("[getPublishedTutorials] - get tutorials by pagination [pages: " + page + ", rows:" + rows + "]");
            repository.findAll(PageRequest.of(page, rows)).forEach(tutorials::add);
        } else {
            logger.info("[getPublishedTutorials] - get all published tutorials");
            repository.findByPublished(true).forEach(tutorials::add);
        }

        //check if the tutorials list is empty
        if (tutorials.isEmpty()) {
            logger.error("[getPublishedTutorials] - tutorial list is empty");
            throw new NullPointerException("No Tutorial found");
        }
        logger.info("[getPublishedTutorials] - started");
        return tutorials;
    }
}
