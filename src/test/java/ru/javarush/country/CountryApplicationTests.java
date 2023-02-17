package ru.javarush.country;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.javarush.country.configuration.HibernateConfiguration;
import ru.javarush.country.dao.CityHibernateDao;
import ru.javarush.country.dao.CountryHibernateDao;
import ru.javarush.country.dto.CityDto;
import ru.javarush.country.dto.CountryDto;
import ru.javarush.country.dto.request.CityByIdRequest;
import ru.javarush.country.dto.request.CityRequest;
import ru.javarush.country.dto.request.CountryRequest;
import ru.javarush.country.mapper.CityMapper;
import ru.javarush.country.mapper.CountryMapper;
import ru.javarush.country.service.CityService;
import ru.javarush.country.service.CityServiceImpl;
import ru.javarush.country.service.CountryService;
import ru.javarush.country.service.CountryServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CountryApplicationTests {

    CityService cityService;
    CountryService countryService;

    @BeforeAll
    void init(){
        runSqlScriptFile("src/test/resources/schema.sql");
        runSqlScriptFile("src/test/resources/data.sql");

        cityService = new CityServiceImpl(new CityMapper(), new CityHibernateDao());
        countryService = new CountryServiceImpl(new CountryMapper(), new CountryHibernateDao());
    }

    private void runSqlScriptFile(String path) {
        try(Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            String sqlScript = new String(Files.readAllBytes(Paths.get(path)));
            session.beginTransaction();
            Query query = session.createNativeQuery(sqlScript);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void checkCitiesCount() {
        Assertions.assertEquals(5, cityService.getCount().getTotalCount());
    }

    @Test
    void checkCityById() {
        CityByIdRequest request = new CityByIdRequest(1);

        Assertions.assertEquals(new CityDto(1,"Kabul","Afghanistan","Kabol",1780000).toString(),
                cityService.getCityById(request).getCity().toString());
    }

    @Test
    void checkCities() {
        CityRequest request = new CityRequest(1,1);
        List<CityDto> cityDtoList= List.of(new CityDto(2,"Qandahar","Afghanistan","Qandahar",237500));

        Assertions.assertEquals(cityDtoList.toString(),
                cityService.getCities(request).getData().toString());
    }

    @Test
    void checkCountries() {
        CountryRequest request = new CountryRequest(1);
        List<CountryDto> countryDtoList= List.of(new CountryDto(1,"ABW","Aruba","NORTH_AMERICA","Caribbean", BigDecimal.valueOf(193.0),103000,BigDecimal.valueOf(78.4),"Herat","Dutch"));

        Assertions.assertEquals(countryDtoList.toString(),
                countryService.getCountries(request).getData().toString());
    }
}