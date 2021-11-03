package org.osstf.tutorial.model;

import javax.persistence.*;

/**
 * Tutorial Entity connected with hibernate and postgresql
 */
@Entity
@Table(name = "tutorials")
public class Tutorial {

    /**
     * Set id as primary key
     * id strategy is to auto generate
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * tutorial title
     */
    @Column(name = "title")
    private String title;

    /**
     * tutorials description
     */
    @Column(name = "description")
    private String description;

    /**
     * tutorial is published or not
     */
    @Column(name = "published")
    private boolean published;

    /**
     * empty constructor
     */
    public Tutorial() {
    }

    /**
     * constructor with mandatory fields
     */
    public Tutorial(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }


    /**
     * setter getters for all the fields
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return is published
     */
    public boolean isPublished() {
        return published;
    }

    /**
     * @param published
     */
    public void setPublished(boolean published) {
        this.published = published;
    }

    /**
     * custom toString method for Tutorial object
     */
    @Override
    public String toString() {
        return "Tutorial{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", published=" + published +
                '}';
    }
}
