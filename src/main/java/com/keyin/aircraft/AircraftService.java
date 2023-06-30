package com.keyin.aircraft;

import com.keyin.airport.Airport;
import com.keyin.airport.AirportService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AircraftService {

    private List<Aircraft> aircraftList = new ArrayList<>();

    public AircraftService() {
       populateList();
    }

    public void populateList() {
        List<Airport> allAirports = new ArrayList<>();
        AirportService airportService = new AirportService();
        allAirports = airportService.getAllAirport();

        Aircraft aircraft1 = new Aircraft();
        aircraft1.setId(1);
        aircraft1.setType("Boeing 737");
        aircraft1.setAirlineName("Air Canada");
        aircraft1.setNumberOfPassengers(100);
        aircraft1.addAllowedAirport(allAirports.get(0));
        aircraft1.addAllowedAirport(allAirports.get(1));
        aircraftList.add(aircraft1);

        Aircraft aircraft2 = new Aircraft();
        aircraft2.setId(2);
        aircraft2.setType("Boeing 101");
        aircraft2.setAirlineName("West Jet");
        aircraft2.setNumberOfPassengers(70);
        aircraftList.add(aircraft2);

        Aircraft aircraft3 = new Aircraft();
        aircraft3.setId(3);
        aircraft3.setType("Boeing 408");
        aircraft3.setAirlineName("Air Canada");
        aircraft3.setNumberOfPassengers(70);
        aircraft3.addAllowedAirport(allAirports.get(2));
        aircraft3.addAllowedAirport(allAirports.get(3));
        aircraft3.addAllowedAirport(allAirports.get(4));
        aircraftList.add(aircraft3);

        Aircraft aircraft4 = new Aircraft();
        aircraft4.setId(4);
        aircraft4.setType("Boeing 709");
        aircraft4.setAirlineName("West Jet");
        aircraft4.setNumberOfPassengers(300);
        aircraft4.addAllowedAirport(allAirports.get(5));
        aircraft4.addAllowedAirport(allAirports.get(6));
        aircraftList.add(aircraft4);

        Aircraft aircraft5 = new Aircraft();
        aircraft5.setId(5);
        aircraft5.setType("Boeing 402");
        aircraft5.setAirlineName("East Jet");
        aircraft5.setNumberOfPassengers(200);
        aircraft5.addAllowedAirport(allAirports.get(5));
        aircraft5.addAllowedAirport(allAirports.get(6));
        aircraft5.addAllowedAirport(allAirports.get(7));
        aircraftList.add(aircraft5);


        Aircraft aircraft6 = new Aircraft();
        aircraft6.setId(6);
        aircraft6.setType("Comet");
        aircraft6.setAirlineName("Private Owned");
        aircraft6.setNumberOfPassengers(6);
        aircraftList.add(aircraft6);

        Aircraft aircraft7 = new Aircraft();
        aircraft7.setId(7);
        aircraft7.setType("BlackBird");
        aircraft7.setAirlineName("Grey Sky");
        aircraft7.setNumberOfPassengers(100);
        aircraft7.addAllowedAirport(allAirports.get(5));
        aircraftList.add(aircraft7);

        Aircraft aircraft8 = new Aircraft();
        aircraft8.setId(8);
        aircraft8.setType("AirBus 777");
        aircraft8.setAirlineName("Air Canada");
        aircraft8.setNumberOfPassengers(190);
        aircraft8.addAllowedAirport(allAirports.get(9));
        aircraft8.addAllowedAirport(allAirports.get(10));
        aircraft8.addAllowedAirport(allAirports.get(11));
        aircraft8.addAllowedAirport(allAirports.get(12));
        aircraftList.add(aircraft8);

        Aircraft aircraft9 = new Aircraft();
        aircraft9.setId(9);
        aircraft9.setType("AirBus 434");
        aircraft9.setAirlineName("West Jet");
        aircraft9.setNumberOfPassengers(100);
        aircraftList.add(aircraft9);


    }

    public List<Aircraft> getAllAircraft() {
        return aircraftList;
    }

    public Aircraft getAircraftById(int id) {

        Aircraft foundCraft = new Aircraft();

        for (Aircraft aircraft : aircraftList) {
            if (aircraft.getId() == id) {
                foundCraft = aircraft;
                return foundCraft;
            }
        }
        return foundCraft;
    }

    public List<Aircraft> searchAircraft(String toSearch){

        List<Aircraft> foundList = new ArrayList<>();

        for (Aircraft aircraft : aircraftList) {
            String idToString = String.valueOf(aircraft.getId());

            if(idToString.equals(toSearch) || aircraft.getType().equals(toSearch) || aircraft.getAirlineName().equals(toSearch)) {
                foundList.add(aircraft);

            }
        }
        return foundList;
    }

    public Aircraft addAircraft(Aircraft aircraft){
        aircraftList.add(aircraft);
        return aircraft;
    }

    public List<Aircraft> updateAircraft(int id, Aircraft aircraftToChange){
        boolean found = false;

        for(Aircraft aircraft : aircraftList) {
            if(aircraft.getId() == id){
                aircraft.setType(aircraftToChange.getType());
                aircraft.setAirlineName(aircraftToChange.getAirlineName());
                aircraft.setNumberOfPassengers(aircraftToChange.getNumberOfPassengers());
                aircraft.setAllowedAirportList(aircraftToChange.getAllowedAirportList());
                found = true;
            }
        }
        if(!found) {
            System.out.println("Sorry, this aircraft does not exist.");
        }
        return aircraftList;
    }

    public List<Aircraft> deleteAircraftById(int id) {

        boolean found = false;

        for (Aircraft aircraft : aircraftList) {
            if (aircraft.getId() == id) {
                aircraftList.remove(aircraft);
                //found = true;
                System.out.println("The aircraft has been deleted");
                return aircraftList;
            }
        }
        if (!found){
            System.out.println("Sorry the aircraft you are trying to delete does not exist.");
        }
        return aircraftList;
    }

    public Aircraft addToAllowedList(String aircraftToAdd, String airportToAdd){
        List<Airport> allAirports = new ArrayList<>();
        AirportService airportService = new AirportService();
        allAirports = airportService.getAllAirport();

        Airport foundAirport = new Airport();

        for (Airport airport : allAirports ) {
            String idToString = String.valueOf(airport.getId());

            if (idToString.equals(airportToAdd) || airport.getName().equals(airportToAdd) || airport.getAreaCode().equals(airportToAdd)){
                foundAirport = airport;

            }
        }

        Aircraft foundAircraft = new Aircraft();

        if (foundAirport.getName() != null) {
            for (Aircraft aircraft : aircraftList) {
                String idToString = String.valueOf(aircraft.getId());
                if (idToString.equals(aircraftToAdd) || aircraft.getType().equals(aircraftToAdd)) {
                    aircraft.addAllowedAirport(foundAirport);
                    foundAircraft = aircraft;
                    return aircraft;
                }
            }
        } else {
            System.out.println("The airport or aircraft you are looking for does not exist.");
        }

        return foundAircraft;
    }

    public Aircraft removeFromAllowedList(String aircraftSelected, String airportToRemove) {
        List<Airport> allAirports = new ArrayList<>();
        AirportService airportService = new AirportService();
        allAirports = airportService.getAllAirport();

        Airport foundAirport = new Airport();

        for (Airport airport : allAirports ) {
            String idToString = String.valueOf(airport.getId());

            if (idToString.equals(airportToRemove) || airport.getName().equals(airportToRemove) || airport.getAreaCode().equals(airportToRemove)){
                foundAirport = airport;
            }
        }

        System.out.println(foundAirport.getName());

        Aircraft foundAircraft = new Aircraft();

        if (foundAirport.getName() != null) {
            for (Aircraft aircraft : aircraftList) {
                String idToString = String.valueOf(aircraft.getId());
                if (idToString.equals(aircraftSelected) || aircraft.getType().equals(aircraftSelected)) {
                    aircraft.removeAllowedAirport(foundAirport);
                    foundAircraft = aircraft;
                    return aircraft;
                }
            }
        } else {
            System.out.println("The airport or aircraft you are looking for does not exist.");
        }

        System.out.println(foundAircraft.getType());

        return foundAircraft;

    }

}
