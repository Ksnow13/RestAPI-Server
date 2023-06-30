package com.keyin.passenger;

import com.keyin.aircraft.Aircraft;
import com.keyin.aircraft.AircraftService;
import com.keyin.airport.Airport;
import com.keyin.airport.AirportService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerService {
    private List<Passenger> passengerList = new ArrayList<>();

    public PassengerService() {
        populate();
    }

    public void populate(){
        Passenger passenger1 = new Passenger();
        passenger1.setId(1);
        passenger1.setFirstname("Kyle");
        passenger1.setLastName("Snow");
        passenger1.setPhoneNumber("(709) 683-4444");
        passenger1.addToAircraftIdsList(1);
        passenger1.addToAirportIdsList(1);
        passenger1.addToAirportIdsList(2);
        passengerList.add(passenger1);

        Passenger passenger2 = new Passenger();
        passenger2 .setId(2);
        passenger2 .setFirstname("Ken");
        passenger2 .setLastName("Chafe");
        passenger2 .setPhoneNumber("(709) 683-4532");
        passenger2.addToAircraftIdsList(1);
        passenger2.addToAircraftIdsList(2);
        passenger2.addToAirportIdsList(2);
        passenger2.addToAirportIdsList(3);
        passengerList.add(passenger2 );

        Passenger passenger3 = new Passenger();
        passenger3.setId(3);
        passenger3.setFirstname("Tyler");
        passenger3.setLastName("Power");
        passenger3.setPhoneNumber("(709) 683-9911");
        passengerList.add(passenger3);

        Passenger passenger4 = new Passenger();
        passenger4.setId(4);
        passenger4.setFirstname("Kayleigh");
        passenger4.setLastName("McGrath");
        passenger4.setPhoneNumber("(709) 683-1231");
        passenger4.addToAircraftIdsList(4);
        passenger4.addToAircraftIdsList(5);
        passenger4.addToAirportIdsList(4);
        passenger4.addToAirportIdsList(6);
        passengerList.add(passenger4);

        Passenger passenger5 = new Passenger();
        passenger5.setId(5);
        passenger5.setFirstname("John");
        passenger5.setLastName("Doe");
        passenger5.setPhoneNumber("(709) 555-6675");
        passenger5.addToAircraftIdsList(7);
        passenger5.addToAircraftIdsList(8);
        passenger5.addToAirportIdsList(8);
        passenger5.addToAirportIdsList(9);
        passengerList.add(passenger5);

        Passenger passenger6 = new Passenger();
        passenger6.setId(6);
        passenger6.setFirstname("Sarah");
        passenger6.setLastName("Dove");
        passenger6.setPhoneNumber("(709) 786-5565");
        passenger6.addToAircraftIdsList(3);
        passenger6.addToAircraftIdsList(2);
        passenger6.addToAirportIdsList(1);
        passenger6.addToAirportIdsList(2);
        passenger6.addToAirportIdsList(2);
        passengerList.add(passenger6);

        Passenger passenger7 = new Passenger();
        passenger7.setId(7);
        passenger7.setFirstname("Sarah");
        passenger7.setLastName("Dove");
        passenger7.setPhoneNumber("(709) 786-5565");
        passenger7.addToAircraftIdsList(1);
        passenger7.addToAirportIdsList(1);
        passengerList.add(passenger7);

        Passenger passenger8 = new Passenger();
        passenger8.setId(8);
        passenger8.setFirstname("Sarah");
        passenger8.setLastName("Dove");
        passenger8.setPhoneNumber("(709) 786-5565");
        passengerList.add(passenger8);
    }

    public List<Passenger> getAllPassenger() {
        return passengerList;
    }

    public Passenger getPassengerById(int id) {

        Passenger foundPassenger = new Passenger();

        for (Passenger passenger : passengerList) {
            if (passenger.getId() == id) {
                foundPassenger = passenger;
                return foundPassenger;
            }
        }
        return foundPassenger;
    }

    // fix this so it isn't case-sensitive
    public List<Passenger> searchPassenger(String toSearch){

        List<Passenger> foundList = new ArrayList<>();

        for (Passenger passenger : passengerList) {
            String idToString = String.valueOf(passenger.getId());

            if(idToString.equals(toSearch) || passenger.getFirstname().equals(toSearch) || passenger.getLastName().equals(toSearch) || passenger.getPhoneNumber().equals(toSearch)) {
                foundList.add(passenger);

            }
        }
        return foundList;
    }

    public Passenger addPassenger(Passenger passenger){
        passengerList.add(passenger);
        return passenger;
    }

    public List<Passenger> updatePassenger(int id, Passenger passengerToChange){
        boolean found = false;

        for(Passenger passenger : passengerList) {
            if(passenger.getId() == id){
                passenger.setFirstname(passengerToChange.getFirstname());
                passenger.setLastName(passengerToChange.getLastName());
                passenger.setPhoneNumber(passengerToChange.getPhoneNumber());
                passenger.setAircraftIdsList(passengerToChange.getAircraftIdsList());
                passenger.setAirportIdsList(passengerToChange.getAirportIdsList());
                found = true;
            }
        }
        if(!found) {
            System.out.println("Sorry, this passenger does not exist.");
        }
        return passengerList;
    }

    public List<Passenger> deletePassengerById(int id) {

        boolean found = false;

        for (Passenger passenger : passengerList) {
            if (passenger.getId() == id) {
                passengerList.remove(passenger);
                //found = true;
                System.out.println("The airport has been deleted");
                return passengerList;
            }
        }
        if (!found){
            System.out.println("Sorry the passenger you are trying to delete does not exist.");
        }
        return passengerList;
    }

    public List<Aircraft> getAircraft(int id){
        List<Aircraft> allAircraftList = new ArrayList<>();
        AircraftService aircraftService = new AircraftService();
        allAircraftList = aircraftService.getAllAircraft();

        List<Aircraft> aircraftFlownOn = new ArrayList<>();

        Passenger foundPassenger = new Passenger();

        for(Passenger passenger: passengerList) {
            if (id == passenger.getId()) {
                foundPassenger = passenger;
            }
        }

        for(Integer i : foundPassenger.getAircraftIdsList() ) {
            for(Aircraft aircraft : allAircraftList) {
                if(i == aircraft.getId()) {
                    aircraftFlownOn.add(aircraft);
                }
            }
        }

        return aircraftFlownOn;
    }

    public List<Airport> getAirports(int id) {
        List<Airport> allAirportsList = new ArrayList<>();
        AirportService airportService = new AirportService();
        allAirportsList = airportService.getAllAirport();

        List<Airport> airportVisited = new ArrayList<>();

        Passenger foundPassenger = new Passenger();

        for(Passenger passenger : passengerList) {
            if (id == passenger.getId()) {
                foundPassenger = passenger;
            }
        }

        for(Integer i : foundPassenger.getAirportIdsList() ) {
            for(Airport airport : allAirportsList) {
                if(i == airport.getId()) {
                    airportVisited.add(airport);
                }
            }
        }

        return airportVisited;
    }
}
