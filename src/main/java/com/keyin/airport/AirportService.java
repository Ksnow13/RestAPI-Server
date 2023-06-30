package com.keyin.airport;


import com.keyin.aircraft.AircraftService;
import com.keyin.city.City;
import com.keyin.city.CityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportService {
    private List<Airport> airportList = new ArrayList<>();

    private List<City> cityList = new ArrayList<>();

    // make a method to do the population instead of in the constructor

    public AirportService() {
        populateList();
    }

    public void populateList(){
        Airport airport1 = new Airport();
        airport1.setId(1);
        airport1.setName("Foggy Airport");
        airport1.setAreaCode("A0A 1G0");
        airport1.setCityId(1);
        airportList.add(airport1);

        Airport airport2 = new Airport();
        airport2.setId(2);
        airport2.setName("Edmonton Airlines");
        airport2.setAreaCode("X0B 1F0");
        airport2.setCityId(2);
        airportList.add(airport2);

        Airport airport3 = new Airport();
        airport3.setId(3);
        airport3.setName("Calgary Airport");
        airport3.setAreaCode("C0W 1GR");
        airport3.setCityId(3);
        airportList.add(airport3);

        Airport airport4 = new Airport();
        airport4.setId(4);
        airport4.setName("NFLD Airport");
        airport4.setAreaCode("Z0F 1GR");
        airport4.setCityId(1);
        airportList.add(airport4);

        Airport airport5 = new Airport();
        airport5.setId(5);
        airport5.setName("FlatLand Airport");
        airport5.setAreaCode("Z0G GG0");
        airport5.setCityId(2);
        airportList.add(airport5);

        Airport airport6 = new Airport();
        airport6.setId(6);
        airport6.setName("Prairie Airlines");
        airport6.setAreaCode("F44T GXP");
        airport6.setCityId(2);
        airportList.add(airport6);

        Airport airport7 = new Airport();
        airport7.setId(7);
        airport7.setName("French Airlines");
        airport7.setAreaCode("S0D Y6R");
        airport7.setCityId(5);
        airportList.add(airport7);

        Airport airport8 = new Airport();
        airport8.setId(8);
        airport8.setName("Maple leaf Airport");
        airport8.setAreaCode("Z0D GX0");
        airport8.setCityId(4);
        airportList.add(airport8);

        Airport airport9 = new Airport();
        airport9.setId(9);
        airport9.setName("Lucky Airport");
        airport9.setAreaCode("FG6 GYY");
        airport9.setCityId(6);
        airportList.add(airport9);

        Airport airport10 = new Airport();
        airport10.setId(10);
        airport10.setName("Fredericton Airport");
        airport10.setAreaCode("t3Q DD0");
        airport10.setCityId(6);
        airportList.add(airport10);

        Airport airport11 = new Airport();
        airport11.setId(11);
        airport11.setName("Dry land Airlines");
        airport11.setAreaCode("H7J t30");
        airport11.setCityId(7);
        airportList.add(airport11);

        Airport airport12 = new Airport();
        airport12.setId(12);
        airport12.setName("Victoria Airport");
        airport12.setAreaCode("N5U FF0");
        airport12.setCityId(8);
        airportList.add(airport12);

        Airport airport13 = new Airport();
        airport13.setId(13);
        airport13.setName("Water Way Airlines");
        airport13.setAreaCode("N5U QQ0");
        airport13.setCityId(8);
        airportList.add(airport13);

    }

    public List<Airport> getAllAirport() {
        return airportList;
    }

    public Airport getAirportById(int id) {

        Airport foundAirport = new Airport();

        for (Airport airport : airportList) {
            if (airport.getId() == id) {
                foundAirport = airport;
                return foundAirport;
            }
        }
        return foundAirport;
    }

    public Airport addAirport(Airport airport){
        airportList.add(airport);
        for (Airport list : airportList) {
            System.out.println(list.getName());

        }
        return airport;
    }

    public List<Airport> searchAirport(String toSearch){

        List<Airport> foundList = new ArrayList<>();

        for (Airport airport : airportList) {
            String idToString = String.valueOf(airport.getId());

            if(idToString.equals(toSearch) || airport.getName().equals(toSearch) || airport.getAreaCode().equals(toSearch)) {
                foundList.add(airport);

            }
        }
        return foundList;
    }

    public List<Airport> updateAirport(int id, Airport airportToChange){
        boolean found = false;

        for(Airport airport : airportList) {
            if(airport.getId() == id){
                airport.setName(airportToChange.getName());
                airport.setAreaCode(airportToChange.getAreaCode());
                airport.setCityId(airportToChange.getCityId());
                found = true;
            }
        }
        if(!found) {
            System.out.println("Sorry, this airport does not exist.");
        }
        return airportList;
    }

    public List<Airport> deleteAirportById(int id) {

        boolean found = false;

        for (Airport airport : airportList) {
            if (airport.getId() == id) {
                airportList.remove(airport);
                //found = true;
                System.out.println("The airport has been deleted");
                return airportList;
            }
        }
        if (!found){
            System.out.println("Sorry the airport you are trying to delete does not exist.");
        }
        return airportList;
    }

    public List<Airport> airportByCityId(int id) {
        CityService cityService = new CityService();

        List<Airport> airportByCityList = new ArrayList<>();

        for (Airport airport : airportList) {
            if (airport.getCityId() == id) {
                airportByCityList.add(airport);
            }
        }

        return airportByCityList;
    }
}
