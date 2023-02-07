package ru.javarush.country.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.javarush.country.entity.request.CityByIdRequest;
import ru.javarush.country.entity.request.CityRequest;
import ru.javarush.country.entity.request.CountryRequest;
import ru.javarush.country.entity.response.CityByIdResponse;
import ru.javarush.country.entity.response.CityResponse;
import ru.javarush.country.entity.response.CountResponse;
import ru.javarush.country.entity.response.CountryResponse;
import ru.javarush.country.service.CityService;
import ru.javarush.country.service.CountryService;

@RestController
public class AppController {
    private final CountryService countryService;
    private final CityService cityService;

    @Autowired
    public AppController(CountryService countryService, CityService cityService) {
        this.countryService = countryService;
        this.cityService = cityService;
    }

    @PostMapping(value = "/countries", produces = "application/json")
    public ResponseEntity<CountryResponse> getAllCountries(@RequestBody CountryRequest request) {
        return new ResponseEntity<>(countryService.getCountries(request), HttpStatus.OK);
    }

    @PostMapping(value = "/cities", produces = "application/json")
    public ResponseEntity<CityResponse> getCities(@RequestBody CityRequest request) {
        return new ResponseEntity<>(cityService.getCities(request), HttpStatus.OK);
    }

    @PostMapping(value = "/count", produces = "application/json")
    public ResponseEntity<CountResponse> getCount() {
        return new ResponseEntity<>(cityService.getCount(), HttpStatus.OK);
    }

    @PostMapping(value = "/city", produces = "application/json")
    public ResponseEntity<CityByIdResponse> getCityById(@RequestBody CityByIdRequest request) {
        return new ResponseEntity<>(cityService.getCityById(request), HttpStatus.OK);
    }
}
