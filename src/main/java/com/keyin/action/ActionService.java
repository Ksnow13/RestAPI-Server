package com.keyin.action;

import com.keyin.aircraft.Aircraft;
import com.keyin.aircraft.AircraftService;
import com.keyin.airport.Airport;
import com.keyin.airport.AirportService;
import com.keyin.city.City;
import com.keyin.city.CityService;
import com.keyin.passenger.Passenger;
import com.keyin.passenger.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Stack;

@Service
public class ActionService {

    private Stack<Action> actionStack = new Stack<>();
    private Stack<Action> redoActionStack = new Stack<>();

    @Autowired
    private AircraftService aircraftService; // Autowired to get the data from AircraftService
    // maybe make these two public ???? *******
    @Autowired
    private AirportService airportService;
    @Autowired
    private CityService cityService;
    @Autowired
    private PassengerService passengerService;

    public void addAction(String objectName, String operation, Map<String, Object> theObject) {
        Action action = new Action(objectName, operation, theObject);
        actionStack.push(action);
        System.out.println("the action has been added to the stack " + actionStack.peek().getObjectName() + " " + actionStack.peek().getOperation() + " object id: " + actionStack.peek().getParameters().get("id"));
    }

    public void undoAction() {

        if(actionStack.size() > 0) {

            switch (actionStack.peek().getObjectName()){
                case "aircraft" :
                    if (actionStack.peek().getOperation() == "create"){
                        aircraftService.deleteAircraftById((Integer) actionStack.peek().getParameters().get("id"));
                        redoActionStack.push(actionStack.peek());
                        actionStack.pop();
                    } else if (actionStack.peek().getOperation() == "delete"){
                        aircraftService.addAircraft(setAircraftFromActionStack());
                        redoActionStack.push(actionStack.peek());
                        actionStack.pop();
                        break;
                    } else {
                        aircraftService.updateAircraft((Integer) actionStack.peek().getParameters().get("id"), setAircraftFromActionStack());
                        redoActionStack.push(actionStack.peek());
                        actionStack.pop();
                        break;
                    }
                    break;
                case "airport":
                    if (actionStack.peek().getOperation() == "create"){
                        airportService.deleteAirportById((Integer) actionStack.peek().getParameters().get("id"));
                        redoActionStack.push(actionStack.peek());
                        actionStack.pop();
                    } else if (actionStack.peek().getOperation() == "delete"){
                        airportService.addAirport(setAirportFromActionStack());
                        redoActionStack.push(actionStack.peek());
                        actionStack.pop();
                        break;
                    } else {
                        airportService.updateAirport((Integer) actionStack.peek().getParameters().get("id"), setAirportFromActionStack());
                        redoActionStack.push(actionStack.peek());
                        actionStack.pop();
                        break;
                    }
                    break;
                case "passenger":
                    if (actionStack.peek().getOperation() == "create"){
                        passengerService.deletePassengerById((Integer) actionStack.peek().getParameters().get("id"));
                        redoActionStack.push(actionStack.peek());
                        actionStack.pop();
                    } else if (actionStack.peek().getOperation() == "delete"){
                        passengerService.addPassenger(setPassengerFromActionStack());
                        redoActionStack.push(actionStack.peek());
                        actionStack.pop();
                    } else {
                        passengerService.updatePassenger((Integer) actionStack.peek().getParameters().get("id"), setPassengerFromActionStack());
                        redoActionStack.push(actionStack.peek());
                        actionStack.pop();
                    }
                    break;
                case "city":
                    if (actionStack.peek().getOperation() == "create"){
                        cityService.deleteCityById((Integer) actionStack.peek().getParameters().get("id"));
                        redoActionStack.push(actionStack.peek());
                        actionStack.pop();
                    } else if (actionStack.peek().getOperation() == "delete"){
                        cityService.addCity(setCityFromActionStack());
                        redoActionStack.push(actionStack.peek());
                        actionStack.pop();
                    } else {
                        cityService.updateCity((Integer) actionStack.peek().getParameters().get("id"), setCityFromActionStack());
                        redoActionStack.push(actionStack.peek());
                        actionStack.pop();
                    }
                    break;
                default :
                    break;
            }

        } else {
            System.out.println("Sorry the stack is empty.");
        }

    }

    // doesn't like when u reach the end
    // and cant get update to work with redo **
    public void redoAction() {
        if(redoActionStack.size() > 0) {

            switch (redoActionStack.peek().getObjectName()){
                case "aircraft" :
                    if (redoActionStack.peek().getOperation() == "create"){
                        aircraftService.addAircraft(setAircraftFromRedoStack());
                        actionStack.push(redoActionStack.peek());
                        redoActionStack.pop();
                    } else if (redoActionStack.peek().getOperation() == "delete"){
                        aircraftService.deleteAircraftById((Integer) redoActionStack.peek().getParameters().get("id"));
                        actionStack.push(redoActionStack.peek());
                        redoActionStack.pop();
                        break;
                    }
                    break;
                case "airport":
                    if (redoActionStack.peek().getOperation() == "create"){
                        airportService.addAirport(setAirportFromRedoStack());
                        actionStack.push(redoActionStack.peek());
                        redoActionStack.pop();
                        break;
                    } else if (redoActionStack.peek().getOperation() == "delete"){
                        airportService.deleteAirportById((Integer) redoActionStack.peek().getParameters().get("id"));
                        actionStack.push(redoActionStack.peek());
                        redoActionStack.pop();
                        break;
                    }
                    break;
                case "passenger":
                    if (redoActionStack.peek().getOperation() == "create"){
                        passengerService.addPassenger(setPassengerFromRedoStack());
                        actionStack.push(redoActionStack.peek());
                        redoActionStack.pop();
                    } else if (redoActionStack.peek().getOperation() == "delete"){
                        passengerService.deletePassengerById((Integer) redoActionStack.peek().getParameters().get("id"));
                        actionStack.push(redoActionStack.peek());
                        redoActionStack.pop();
                    }
                    break;
                case "city":
                    if (redoActionStack.peek().getOperation() == "create"){
                        cityService.addCity(setCityFromRedoStack());
                        actionStack.push(redoActionStack.peek());
                        redoActionStack.pop();
                    } else if (redoActionStack.peek().getOperation() == "delete"){
                        cityService.deleteCityById((Integer) redoActionStack.peek().getParameters().get("id"));
                        actionStack.push(redoActionStack.peek());
                        redoActionStack.pop();
                    }
                default :
                    System.out.println("Sorry, something went wrong...");
                    break;
            }

        } else {
            System.out.println("Sorry the stack is empty.");
        }
    }



    public Aircraft setAircraftFromActionStack(){
        Aircraft newAircraft = new Aircraft();
        newAircraft.setId((Integer) actionStack.peek().getParameters().get("id"));
        newAircraft.setType((String) actionStack.peek().getParameters().get("type"));
        newAircraft.setAirlineName((String) actionStack.peek().getParameters().get("airlineName"));
        newAircraft.setNumberOfPassengers((Integer) actionStack.peek().getParameters().get("numberOfPassengers"));
        newAircraft.setAllowedAirportList((List<Airport>) actionStack.peek().getParameters().get("allowedAirportList"));
        return newAircraft;
    }

    public Aircraft setAircraftFromRedoStack(){
        Aircraft newAircraft = new Aircraft();
        newAircraft.setId((Integer) redoActionStack.peek().getParameters().get("id"));
        newAircraft.setType((String) redoActionStack.peek().getParameters().get("type"));
        newAircraft.setAirlineName((String) redoActionStack.peek().getParameters().get("airlineName"));
        newAircraft.setNumberOfPassengers((Integer) redoActionStack.peek().getParameters().get("numberOfPassengers"));
        newAircraft.setAllowedAirportList((List<Airport>) redoActionStack.peek().getParameters().get("allowedAirportList"));
        return newAircraft;
    }

    public Airport setAirportFromActionStack(){
        Airport newAirport = new Airport();
        newAirport.setId((Integer) actionStack.peek().getParameters().get("id"));
        newAirport.setName((String) actionStack.peek().getParameters().get("name"));
        newAirport.setAreaCode((String) actionStack.peek().getParameters().get("areaCode"));
        newAirport.setCityId((Integer) actionStack.peek().getParameters().get("cityId"));
        return newAirport;
    }

    public Airport setAirportFromRedoStack(){
        Airport newAirport = new Airport();
        newAirport.setId((Integer) redoActionStack.peek().getParameters().get("id"));
        newAirport.setName((String) redoActionStack.peek().getParameters().get("name"));
        newAirport.setAreaCode((String) redoActionStack.peek().getParameters().get("areaCode"));
        newAirport.setCityId((Integer) redoActionStack.peek().getParameters().get("cityId"));
        return newAirport;
    }

    public City setCityFromActionStack(){
        City newCity = new City();
        newCity.setId((Integer) actionStack.peek().getParameters().get("id"));
        newCity.setName((String) actionStack.peek().getParameters().get("name"));
        newCity.setProvince((String) actionStack.peek().getParameters().get("province"));
        newCity.setPopulation((Integer) actionStack.peek().getParameters().get("population"));
        return newCity;
    }

    public City setCityFromRedoStack(){
        City newCity = new City();
        newCity.setId((Integer) redoActionStack.peek().getParameters().get("id"));
        newCity.setName((String) redoActionStack.peek().getParameters().get("name"));
        newCity.setProvince((String) redoActionStack.peek().getParameters().get("province"));
        newCity.setPopulation((Integer) redoActionStack.peek().getParameters().get("population"));
        return newCity;
    }

    public Passenger setPassengerFromActionStack(){
        Passenger newPassenger = new Passenger();
        newPassenger.setId((Integer) actionStack.peek().getParameters().get("id"));
        newPassenger.setFirstname((String) actionStack.peek().getParameters().get("firstname"));
        newPassenger.setLastName((String) actionStack.peek().getParameters().get("lastName"));
        newPassenger.setPhoneNumber((String) actionStack.peek().getParameters().get("phoneNumber"));
        newPassenger.setAircraftIdsList((List<Integer>) actionStack.peek().getParameters().get("aircraftIdsList"));
        newPassenger.setAirportIdsList((List<Integer>) actionStack.peek().getParameters().get("airportIdsList"));
        return newPassenger;
    }

    public Passenger setPassengerFromRedoStack(){
        Passenger newPassenger = new Passenger();
        newPassenger.setId((Integer) redoActionStack.peek().getParameters().get("id"));
        newPassenger.setFirstname((String) redoActionStack.peek().getParameters().get("firstname"));
        newPassenger.setLastName((String) redoActionStack.peek().getParameters().get("lastName"));
        newPassenger.setPhoneNumber((String) redoActionStack.peek().getParameters().get("phoneNumber"));
        newPassenger.setAircraftIdsList((List<Integer>) redoActionStack.peek().getParameters().get("aircraftIdsList"));
        newPassenger.setAirportIdsList((List<Integer>) redoActionStack.peek().getParameters().get("airportIdsList"));
        return newPassenger;
    }
}
