package com.rmaurya.Aircraft;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class AircraftService {
    private final AircraftRepository aircraftRepository;
    private final WebClient webClient = WebClient.create("http://localhost:7369/aircraft");
    //save aircraft position
    public Flux<Aircraft> saveAircraftPosition() {
        //Reactive Code
        Flux<Aircraft> aircraftFlux = aircraftRepository.deleteAll()
                .thenMany(webClient.get()
                        .retrieve()
                        .bodyToFlux(Aircraft.class)
                        .filter(plane -> !plane.getReg().isEmpty())
                        .flatMap(aircraftRepository::save));
        return aircraftFlux;
    }

    //get aircraft position
    public Flux<Aircraft> getAircraftPosition() {
        return aircraftRepository.findAll();
    }
}
