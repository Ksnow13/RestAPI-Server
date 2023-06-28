package com.keyin.passenger;

import com.keyin.airport.Airport;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Passenger {
    private int id;
    private String firstname;
    private String lastName;
    private String phoneNumber;
    private List<Integer> aircraftIdsList = new ArrayList<>();
    private List<Integer> airportIdsList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Integer> getAircraftIdsList() {
        return aircraftIdsList;
    }

    public List<Integer> getAirportIdsList() {
        return airportIdsList;
    }

    public void setAircraftIdsList(List<Integer> aircraftIdsList) {
        this.aircraftIdsList = aircraftIdsList;
    }

    public void setAirportIdsList(List<Integer> airportIdsList) {
        this.airportIdsList = airportIdsList;
    }

    public void addToAircraftIdsList(int id){
        aircraftIdsList.add(id);
    }

    public void removeFromAircraftIdsList(int id){
        for (Integer match : aircraftIdsList) {
            if(match == id){
                aircraftIdsList.remove(match);
                System.out.println("The aircraft has been removed from the list");
            }
        }
    }

    public void addToAirportIdsList(int id){
        airportIdsList.add(id);
    }

    // used in service yet
    public void removeFromAirportIdsList(int id){
        for (Integer match : airportIdsList) {
            if(match == id){
                airportIdsList.remove(match);
                System.out.println("The airport has been removed from the list");
            }
        }
    }

}
