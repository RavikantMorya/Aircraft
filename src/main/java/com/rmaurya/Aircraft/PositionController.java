package com.rmaurya.Aircraft;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Controller
public class PositionController {
    @NonNull
    private final AircraftService aircraftService;

    @GetMapping("/position")
    public Mono<String> getCurrentAircraftPositions(Model model) {

        return aircraftService.saveAircraftPosition().thenMany(aircraftService.getAircraftPosition())
                .collectList().map(
                        aircrafts -> {
                            model.addAttribute("aircrafts", aircrafts);
                            return "position";
                        }
                );
    }
}