package com.keyin;

import com.keyin.airport.Airport;
import com.keyin.airport.AirportService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Airport> newAirportList = new ArrayList<>();

        AirportService airportService = new AirportService();

        newAirportList = airportService.getAllAirport();

        for (Airport airport : newAirportList) {
            System.out.println(airport.getName());
        }

        System.out.println();
        System.out.println();
        System.out.println(newAirportList.get(0).getName());

        System.out.println();
        System.out.println();

        Airport xxx = newAirportList.get(0);



        newAirportList.remove(xxx);

        System.out.println();
        System.out.println("new test here");
        System.out.println();

        for (Airport airport : newAirportList) {
            System.out.println(airport.getName());
        }

        Airport ar = new Airport();

        ar.setId(1);
        ar.setName("bayRoberts");
        ar.setAreaCode("aaaaa");
        ar.setCityId(4);

        System.out.println(ar.toString());

    }
}
