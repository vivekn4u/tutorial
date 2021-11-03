package org.osstf.tutorial.model;

import java.util.Map;

/**
 * Weather Astronomy entity
 */
public class WeatherAstronomy {
    private Location location;
    private Map<String, Astronomy> astronomy;

    /**
     * Empty constructor
     */
    public WeatherAstronomy() {
    }

    /**
     * @param location
     * @param astronomy
     */
    public WeatherAstronomy(Location location, Map<String, Astronomy> astronomy) {
        this.location = location;
        this.astronomy = astronomy;
    }

    /**
     * @return location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return Map of string and Astronomy object
     */
    public Map<String, Astronomy> getAstronomy() {
        return astronomy;
    }

    /**
     * @param astronomy
     */
    public void setAstronomy(Map<String, Astronomy> astronomy) {
        this.astronomy = astronomy;
    }

    /**
     * @return get Astronomy object
     */
    public Astronomy getAstro() {
        return astronomy.get("astro");
    }
}
