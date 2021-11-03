package org.osstf.tutorial.service;

import org.osstf.tutorial.model.Astronomy;
import org.osstf.tutorial.model.WeatherAstronomy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


/**
 * Astronomical Services implementation class
 */
@Service
public class AstronomyServiceImpl implements AstronomyService {

    Logger logger = LoggerFactory.getLogger(AstronomyServiceImpl.class);

    /**
     * Auto wire Web Client Builder been
     */
    @Autowired
    private WebClient.Builder webClientBuilder;


    /**
     * @return Astronomy as per location
     */
    @Override
    public Astronomy getAstronomyDetailsAsPerLocation() {
        logger.info("[getAstronomyDetailsAsPerLocation] - started");
        WeatherAstronomy weatherAstronomy = webClientBuilder.build()
                .get()
                .uri("https://api.weatherapi.com/v1/astronomy.json?key=145de4cdb3244284abc204828210211 &q=India&dt=2021-11-02")
                .retrieve()
                .bodyToMono(WeatherAstronomy.class)
                .block();
        logger.info("[getAstronomyDetailsAsPerLocation] - End");

        return weatherAstronomy.getAstro();
    }
}
