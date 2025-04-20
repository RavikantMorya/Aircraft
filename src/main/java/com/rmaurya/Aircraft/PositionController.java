package com.rmaurya.Aircraft;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Controller
public class PositionController {
    @NonNull
    private final AircraftService aircraftService;

    @GetMapping("/position")
    public String getCurrentAircraftPositions(Model model) {
        aircraftService.saveAircraftPosition();
        model.addAttribute("aircrafts",aircraftService.getAircraftPosition());
        return "position";
    }
}