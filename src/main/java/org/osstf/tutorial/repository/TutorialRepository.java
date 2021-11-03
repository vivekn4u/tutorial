package org.osstf.tutorial.repository;

import org.osstf.tutorial.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Tutorials repository interface
 */
public interface TutorialRepository extends JpaRepository<Tutorial,Long> {
    /**
     * @param published
     * @return List of tutorial
     */
    List<Tutorial> findByPublished(boolean published);

    /**
     * @param title
     * @return List of tutorial
     */
    List<Tutorial> findByTitleContaining(String title);
}
