package org.osstf.tutorial.controller;

import org.osstf.tutorial.model.Astronomy;
import org.osstf.tutorial.model.Tutorial;
import org.osstf.tutorial.service.AstronomyServiceImpl;
import org.osstf.tutorial.service.TutorialServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * declare all urls in controller will start with /api
 * the return values of all methods will bound to web response body
 */
@RestController
@RequestMapping("/api")
public class TutorialController {

    Logger logger = LoggerFactory.getLogger(TutorialController.class);

    /**
     * Auto wire to inject Tutorial service
     */
    @Autowired
    TutorialServiceImpl tutorialService;

    /**
     * Auto wire to inject Astronomy service
     */
    @Autowired
    AstronomyServiceImpl astronomyService;


    /**
     * HTTP Get method to retrieve the information of all tutorials
     *
     * @param title optional
     * @param page optional
     * @param rows optional
     * @return List of tutorials
     * @auther Vivek Nakalgaonkar
     */
    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title,
                                                          @RequestParam(required = false) Integer page,
                                                          @RequestParam(required = false) Integer rows) {
        logger.info("[getAllTutorials] info message");
        try {
            List<Tutorial> list = tutorialService.getTutorials(title, page, rows);
            if (list.isEmpty()) {
                logger.error("[getAllTutorials] Empty List");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.info("[getAllTutorials] End");
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("[getAllTutorials] - "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Http Get method to get tutorial by id
     *
     * @param id
     * @return Tutorial
     * @request Http Get methode to retrieve tutorial by id
     * @auther Vivek Nakalgaonkar
     */
    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
        logger.info("[getTutorialById] start");
        Optional<Tutorial> tutorial = tutorialService.getTutorial(id);
        if (tutorial.isPresent()) {
            logger.info("[getTutorialById] end");
            return new ResponseEntity<>(tutorial.get(), HttpStatus.OK);
        } else {
            logger.error("[getTutorialById] Empty object");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     * Http Post method to create new Tutorial
     *
     * @param tutorial
     * @return Tutorial
     * @auther Vivek Nakalgaonkar
     */
    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        logger.info("[createTutorial] start");
        Tutorial tutorial1 = tutorialService.createTutorial(tutorial);
        try {
            logger.info("[createTutorial] end");
            return new ResponseEntity<>(tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("[createTutorial] - "+ e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Http put method to update the data by sending id and Tutorial data
     *
     * @param id
     * @param tutorial
     * @return Tutorial
     * @auther Vivek Nakalgaonkar
     */
    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        logger.info("[updateTutorial] start");
        Optional<Tutorial> tutorialData = tutorialService.updateTutorial(id, tutorial);
        if (tutorialData.isPresent()) {
            logger.info("[updateTutorial] end");
            return new ResponseEntity<>(tutorial, HttpStatus.OK);
        } else {
            logger.error("[updateTutorial] Empty object");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Http Delete method to delete a tutorial by id
     *
     * @param id
     * @return HTTPStatus
     * @auther Vivek Nakalgaonkar
     */
    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        logger.info("[deleteTutorial] start");
        try {
            boolean status = tutorialService.deleteTutorial(id);
            logger.debug("[deleteTutorial] delete status is "+ status);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("[deleteTutorial] - "+ e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Http Get method to get all published tutorials
     *
     * @param page optional
     * @param rows optional
     * @return List of Tutorials
     * @auther Vivek Nakalgaonkar
     */
    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> getPublishedTutorials(@RequestParam(required = false) Integer page,
                                                                @RequestParam(required = false) Integer rows) {
        logger.info("[getPublishedTutorials] start");
        try {
            List<Tutorial> list = tutorialService.getPublishedTutorials(page, rows);
            if (list.isEmpty()) {
                logger.error("[getPublishedTutorials] end - List is empty");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.info("[getPublishedTutorials] end");
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("[getPublishedTutorials] - "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get the astronomical details as per location
     *
     * @return Astronomy
     * @auther Vivek Nakalgaonkar
     */
    @GetMapping("/astronomy")
    public ResponseEntity<Astronomy> getAstronomyDetailsAsPerLocation() {
        logger.info("[getAstronomyDetailsAsPerLocation] start");
        try {
            Astronomy astronomy = astronomyService.getAstronomyDetailsAsPerLocation();
            logger.info("[getAstronomyDetailsAsPerLocation] end");
            return new ResponseEntity<>(astronomy, HttpStatus.OK);
        } catch (Exception e) {
            //System.out.println("Astronomy api call: " + e.getMessage());
            logger.error("[getAstronomyDetailsAsPerLocation] - "+ e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
