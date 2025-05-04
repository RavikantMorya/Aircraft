package com.rmaurya.Aircraft;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
//@RestController IF YOU ARE USING TEST CLASSES THEN USE RESTCONTROLLER AND THEIR APIs
public class PositionController {
    @NonNull
    private final AircraftService aircraftService;

    //controller for using position.html
    @GetMapping("/user/position")
    public String getCurrentAircraftPositionsUSER(Model model) {
        model.addAttribute("aircrafts",aircraftService.getAircraftPosition());
        return "position";
    }

    @GetMapping("/aircraftadmin/position")
    public String getCurrentAircraftPositionsADMIN(Model model) {
        model.addAttribute("aircrafts",aircraftService.getAircraftPosition());
        return "position";
    }

    //controller to be called through RestApi for User
    @GetMapping("/user/api/position")
    public Iterable<Aircraft> getCurrentAircraftPositionsUserApi() {
    return  aircraftService.getAircraftPosition();
    }

    //controller to be called through RestApi for ADMIN
    @GetMapping("/aircraftadmin/api/position")
    public Iterable<Aircraft> getCurrentAircraftPositionsAdminApi() {
        return  aircraftService.getAircraftPosition();
    }

}