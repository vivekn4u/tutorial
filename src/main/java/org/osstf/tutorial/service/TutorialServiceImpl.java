package org.osstf.tutorial.service;

import org.osstf.tutorial.model.Tutorial;
import org.osstf.tutorial.repository.TutorialRepository;
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
        try {
            Tutorial tutorialData = repository.
                    save(new Tutorial(tutorial.getTitle(),
                            tutorial.getDescription(),
                            tutorial.isPublished())
                    );
            return tutorialData;
        } catch (Exception e){
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
        Optional<Tutorial> tutorialData = repository.findById(id);

        if(tutorialData.isPresent()){
            Tutorial _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());

            return  Optional.of(repository.save(_tutorial));
        } else {
            //return empty Optional object
            return tutorialData;
        }
    }

    /**
     * @param id
     * @return boolean status
     */
    @Override
    public boolean deleteTutorial(long id) {
        try {
            repository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /**
     * @return List of tutorials
     */
    @Override
    public List<Tutorial> getTutorials(String title, Integer page, Integer rows) {

        List<Tutorial> tutorials = new ArrayList<Tutorial>();
        /*
         * Check if the title is empty then add all records in list else
         * find the title and add to list
         * similarly check for pagination else return all objects list
         * */
        if (title != null ) {
            repository.findByTitleContaining(title).forEach(tutorials::add);
        } else if (page != null && rows != null) {
            repository.findAll(PageRequest.of(page,rows)).forEach(tutorials::add);
        } else {
            repository.findAll().forEach(tutorials::add);
        }

        /*
         * if List is empty throw an exception
         * */
        if (tutorials.isEmpty()) {
            throw new NullPointerException("Error occurred while getting tutorials");
        }

        return tutorials;

    }

    /**
     * @param id
     * @return Optional tutorial object for not-null check
     */
    @Override
    public Optional<Tutorial> getTutorial(long id) {
        Optional<Tutorial> tutorial = repository.findById(id);
        return tutorial;
    }

    /**
     * @return List of Tutorials
     */
    @Override
    public List<Tutorial> getPublishedTutorials(Integer page, Integer rows) {
        List<Tutorial> tutorials = new ArrayList<Tutorial>();

        /*
        * check for pagination else return all objects
        * */
        if (page != null && rows != null ) {
            repository.findAll(PageRequest.of(page,rows)).forEach(tutorials::add);
        } else {
            repository.findByPublished(true).forEach(tutorials::add);
        }

        //check if the tutorials list is empty
        if (tutorials.isEmpty()) {
            throw new NullPointerException("No Tutorial found");
        }
        return tutorials;
    }
}
