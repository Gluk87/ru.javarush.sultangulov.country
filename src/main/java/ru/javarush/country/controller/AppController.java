package ru.javarush.country.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.javarush.country.dto.request.CityByIdRequest;
import ru.javarush.country.dto.request.CityRequest;
import ru.javarush.country.dto.request.CountryRequest;
import ru.javarush.country.dto.response.CityByIdResponse;
import ru.javarush.country.dto.response.CityResponse;
import ru.javarush.country.dto.response.CountResponse;
import ru.javarush.country.dto.response.CountryResponse;
import ru.javarush.country.service.CityService;
import ru.javarush.country.service.CountryService;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AppController {

    private final CountryService countryService;
    private final CityService cityService;

    @PostMapping(value = "/country/all", produces = "application/json")
    public ResponseEntity<CountryResponse> getAllCountries(@RequestBody CountryRequest request) {
        return new ResponseEntity<>(countryService.getCountries(request), HttpStatus.OK);
    }

    @PostMapping(value = "/city/all", produces = "application/json")
    public ResponseEntity<CityResponse> getCities(@RequestBody CityRequest request) {
        return new ResponseEntity<>(cityService.getCities(request), HttpStatus.OK);
    }

    @PostMapping(value = "/city/count", produces = "application/json")
    public ResponseEntity<CountResponse> getCount() {
        return new ResponseEntity<>(cityService.getCount(), HttpStatus.OK);
    }

    @PostMapping(value = "/city", produces = "application/json")
    public ResponseEntity<CityByIdResponse> getCityById(@RequestBody CityByIdRequest request) {
        return new ResponseEntity<>(cityService.getCityById(request), HttpStatus.OK);
    }
}
