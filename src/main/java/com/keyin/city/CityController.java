package com.keyin.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/city")
    public List<City> getAllCity() {
        return cityService.getAllCity();
    }

    @GetMapping("/city/{id}")
    public City getCityById(@PathVariable int id) {
        return cityService.getCityById(id);
    }

    @GetMapping("/city/search")
    public List<City> searchAirport(@RequestParam String toSearch){
        return cityService.searchCity(toSearch);
    }

    @PostMapping("/city/addCity")
    public void addCity(@RequestBody City city){
        cityService.addCity(city);
    }

    @DeleteMapping("/city/deleteCity/{id}")
    public List<City> deleteCityById(@PathVariable int id) {
        return cityService.deleteCityById(id);
    }

    @PutMapping("/city/updateCity/{id}")
    public List<City> updateCity(@PathVariable int id, @RequestBody City city){
        return cityService.updateCity(id, city);
    }
}
