package com.keyin.passenger;


import com.keyin.aircraft.Aircraft;
import com.keyin.airport.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @GetMapping("/passenger")
    public List<Passenger> getAllPassenger() {
        return passengerService.getAllPassenger();
    }

    @GetMapping("/passenger/{id}")
    public Passenger getPassengerById(@PathVariable int id) {
        return passengerService.getPassengerById(id);
    }

    @GetMapping("/passenger/search")
    public List<Passenger> searchPassenger(@RequestParam String toSearch){
        return passengerService.searchPassenger(toSearch);
    }

    @PostMapping("/passenger/addPassenger")
    public void addPassenger(@RequestBody Passenger passenger){
        passengerService.addPassenger(passenger);
    }

    @DeleteMapping("/passenger/deletePassenger/{id}")
    public List<Passenger> deletePassengerById(@PathVariable int id) {
        return passengerService.deletePassengerById(id);
    }

    @PutMapping("/passenger/updatePassenger/{id}")
    public List<Passenger> updatePassenger(@PathVariable int id, @RequestBody Passenger passenger){
        return passengerService.updatePassenger(id, passenger);
    }

    @GetMapping("/passenger/{id}/getAircraft")
    public List<Aircraft> getAircraftPassengerTravelledOn(@PathVariable int id) {
        return passengerService.getAircraft(id);
    }

    @GetMapping("/passenger/{id}/getAirport")
    public List<Airport> getAirportPassengerVisited(@PathVariable int id) {
        return passengerService.getAirports(id);
    }
}
