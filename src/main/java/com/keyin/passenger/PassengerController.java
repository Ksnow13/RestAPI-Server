package com.keyin.passenger;


import com.keyin.action.ActionService;
import com.keyin.aircraft.Aircraft;
import com.keyin.airport.Airport;
import com.keyin.history.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class PassengerController {
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ActionService actionService;

    @GetMapping("/passenger")
    public List<Passenger> getAllPassenger() {
        historyService.addToHistory("getAllPassenger()", "/passenger", LocalDateTime.now());
        return passengerService.getAllPassenger();
    }

    @GetMapping("/passenger/{id}")
    public Passenger getPassengerById(@PathVariable int id) {
        String url = "/passenger/" + String.valueOf(id);
        historyService.addToHistory("getPassengerById()", url, LocalDateTime.now());
        return passengerService.getPassengerById(id);
    }

    @GetMapping("/passenger/search")
    public List<Passenger> searchPassenger(@RequestParam String toSearch){
        historyService.addToHistory("searchPassenger()", "passenger/search", LocalDateTime.now());
        return passengerService.searchPassenger(toSearch);
    }

    @PostMapping("/passenger/addPassenger")
    public void addPassenger(@RequestBody Passenger passenger){
        actionService.addAction("passenger", "create", Map.of("id", passenger.getId(), "firstname",  passenger.getFirstname(), "lastName", passenger.getLastName(), "phoneNumber", passenger.getPhoneNumber(), "aircraftIdsList", passenger.getAircraftIdsList(), "airportIdsList", passenger.getAirportIdsList()));
        historyService.addToHistory("addPassenger()", "/passenger/addPassenger", LocalDateTime.now());
        passengerService.addPassenger(passenger);
    }

    @DeleteMapping("/passenger/deletePassenger/{id}")
    public List<Passenger> deletePassengerById(@PathVariable int id) {
        Passenger passengerForAction = new Passenger();
        List<Passenger> passengerlist = passengerService.getAllPassenger();
        for (Passenger passenger : passengerlist){
            if (passenger.getId() == id) {
                passengerForAction = passenger;
            }
        }
        if (passengerForAction != null) {
            actionService.addAction("passenger", "delete", Map.of("id", passengerForAction.getId(), "firstname",  passengerForAction.getFirstname(), "lastName", passengerForAction.getLastName(), "phoneNumber", passengerForAction.getPhoneNumber(), "aircraftIdsList", passengerForAction.getAircraftIdsList(), "airportIdsList", passengerForAction.getAirportIdsList()));
        }

        String url = "/passenger/deletePassenger/" + String.valueOf(id);
        historyService.addToHistory("deletePassenger()", url, LocalDateTime.now());
        return passengerService.deletePassengerById(id);
    }

    @PutMapping("/passenger/updatePassenger/{id}")
    public List<Passenger> updatePassenger(@PathVariable int id, @RequestBody Passenger passenger){
        Passenger passengerForAction = new Passenger();
        List<Passenger> passengerlist = passengerService.getAllPassenger();
        for (Passenger passengerToFind : passengerlist){
            if (passengerToFind.getId() == id) {
                passengerForAction = passengerToFind;
            }
        }
        if (passengerForAction != null) {
            actionService.addAction("passenger", "update", Map.of("id", passengerForAction.getId(), "firstname",  passengerForAction.getFirstname(), "lastName", passengerForAction.getLastName(), "phoneNumber", passengerForAction.getPhoneNumber(), "aircraftIdsList", passengerForAction.getAircraftIdsList(), "airportIdsList", passengerForAction.getAirportIdsList()));
        }

        String url = "/passenger/updatePassenger/" + String.valueOf(id);
        historyService.addToHistory("updatePassenger()", url, LocalDateTime.now());
        return passengerService.updatePassenger(id, passenger);
    }

    @GetMapping("/passenger/{id}/getAircraft")
    public List<Aircraft> getAircraftPassengerTravelledOn(@PathVariable int id) {
        String url = "/passenger/"  + String.valueOf(id) + "/getAircraft";
        historyService.addToHistory("getAircraft()", url, LocalDateTime.now());
        return passengerService.getAircraft(id);
    }

    @GetMapping("/passenger/{id}/getAirport")
    public List<Airport> getAirportPassengerVisited(@PathVariable int id) {
        String url = "/passenger/"  + String.valueOf(id) + "/getAirport";
        historyService.addToHistory("getAirport()", url, LocalDateTime.now());
        return passengerService.getAirports(id);
    }
}
