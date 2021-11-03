package org.osstf.tutorial.service;

import org.osstf.tutorial.model.Astronomy;

/**
 * Interface to provide Astronomical services
 */
public interface AstronomyService {

    /**
     * @return Astronomy object as per location
     */
    public Astronomy getAstronomyDetailsAsPerLocation();

}
