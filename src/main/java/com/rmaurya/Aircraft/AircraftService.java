package com.rmaurya.Aircraft;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class AircraftService {
    private final AircraftRepository aircraftRepository;
    private final WebClient webClient = WebClient.create("http://localhost:7369/aircraft");
    //save aircraft position
    public void saveAircraftPosition() {
        aircraftRepository.deleteAll();
        webClient.get()
                .retrieve()
                .bodyToFlux(Aircraft.class)
                .filter(plane -> !plane.getReg().isEmpty())
                .toStream()
                .forEach(aircraftRepository::save);
    }

    //get aircraft position
    public Iterable<Aircraft> getAircraftPosition() {
        return aircraftRepository.findAll();
    }
}
