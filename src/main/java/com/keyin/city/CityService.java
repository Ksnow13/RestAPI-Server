package com.keyin.city;

import com.keyin.airport.Airport;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {
    private List<City> cityList = new ArrayList<>();

    public CityService() {
     populateList();
    }

    public void populateList() {
        City city1 = new City();
        city1.setId(1);
        city1.setName("St Johns");
        city1.setProvince("Newfoundland");
        city1.setPopulation(100_000);
        cityList.add(city1);

        City city2 = new City();
        city2.setId(2);
        city2.setName("Edmonton");
        city2.setProvince("Alberta");
        city2.setPopulation(100_000_000);
        cityList.add(city2);

        City city3 = new City();
        city3.setId(3);
        city3.setName("Calgary");
        city3.setProvince("Alberta");
        city3.setPopulation(200_000_000);
        cityList.add(city3);
    }

    public List<City> getAllCity() {
        return cityList;
    }

    public City getCityById(int id) {

        City foundCity = new City();

        for (City city : cityList) {
            if (city.getId() == id) {
                foundCity = city;
                return foundCity;
            }
        }
        return foundCity;
    }

    public List<City> searchCity(String toSearch){

        List<City> foundList = new ArrayList<>();

        for (City city : cityList) {
            String idToString = String.valueOf(city.getId());

            if(idToString.equals(toSearch) || city.getName().equals(toSearch) || city.getProvince().equals(toSearch)) {
                foundList.add(city);

            }
        }
        return foundList;
    }

    public City addCity(City city){
        cityList.add(city);
        return city;
    }

    public List<City> deleteCityById(int id) {

        boolean found = false;

        for (City city : cityList) {
            if (city.getId() == id) {
                cityList.remove(city);
                //found = true;
                System.out.println("The city has been deleted");
                return cityList;
            }
        }
        if (!found){
            System.out.println("Sorry the city you are trying to delete does not exist.");
        }
        return cityList;
    }

    public List<City> updateCity(int id, City cityToChange){
        boolean found = false;

        for(City city : cityList) {
            if(city.getId() == id){
                city.setName(cityToChange.getName());
                city.setProvince(cityToChange.getProvince());
                city.setPopulation(cityToChange.getPopulation());
                found = true;
            }
        }
        if(!found) {
            System.out.println("Sorry, the city you are trying to update does not exist.");
        }
        return cityList;
    }

}
