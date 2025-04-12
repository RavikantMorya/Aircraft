package com.example.MyAircraft;//package com.example.MyAircraft;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@RequiredArgsConstructor
//@Controller
//public class PositionController {
//    @NonNull
//    private final AircraftRepository repository;
//    private WebClient client =
//            WebClient.create("http://localhost:7369/aircraft");
//
//    @GetMapping("/position")
//    public String getCurrentAircraftPositions(Model model) {
//        repository.deleteAll();
//        client.get()
//                .retrieve()
//                .bodyToFlux(Aircraft.class)
//                .filter(plane -> !plane.getReg().isEmpty())
//                .toStream()
//                .forEach(repository::save);
//        model.addAttribute("aircrafts", repository.findAll());
//        return "position";
//    }
//}
@RequiredArgsConstructor
@Controller
public class PositionController {
    @NonNull
    private final AircraftRepository aircraftRepository;
    @GetMapping("/position")
    public String getCurrentAircraftPositions(Model model) {
        model.addAttribute("aircrafts", aircraftRepository.findAll());
        return "position";
    }

}
