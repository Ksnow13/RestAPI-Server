package com.keyin.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping("/airport")
    public List<Airport> getAllAirport() {
        return airportService.getAllAirport();
    }

    @GetMapping("/airport/{id}")
    public Airport getAirportById(@PathVariable int id) {
        return airportService.getAirportById(id);
    }

    @GetMapping("/airport/search")
    public List<Airport> searchAirport(@RequestParam String toSearch){
        return airportService.searchAirport(toSearch);
    }

    @PostMapping("/airport/addAirport")
    public void addAirport(@RequestBody Airport airport){
        airportService.addAirport(airport);
    }

    @DeleteMapping("/airport/deleteAirport/{id}")
    public List<Airport> deleteAirportById(@PathVariable int id) {
        return airportService.deleteAirportById(id);
    }

    @PutMapping("/airport/updateAirport/{id}")
    public List<Airport> updateAirport(@PathVariable int id, @RequestBody Airport airport){
        return airportService.updateAirport(id, airport);
    }

    //relationship
    @GetMapping("/airport/getByCityId/{id}")
    public List<Airport> airportByCityId(@PathVariable int id) {
        return airportService.airportByCityId(id);
    }

}