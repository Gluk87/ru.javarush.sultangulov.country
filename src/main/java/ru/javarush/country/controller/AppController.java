package ru.javarush.country.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.javarush.country.entity.CountryRequest;
import ru.javarush.country.entity.CountryResponse;
import ru.javarush.country.service.CountryService;

@RestController
public class AppController {
    private final CountryService countryService;

    @Autowired
    public AppController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping(value = "/all_countries", produces = "application/json")
    public ResponseEntity<CountryResponse> getAllCountries(@RequestBody CountryRequest request) {
        return new ResponseEntity<>(countryService.getCountries(request), HttpStatus.OK);
    }
}
