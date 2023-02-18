package ru.javarush.country.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.javarush.country.dao.CityDao;
import ru.javarush.country.dao.CityHibernateDao;
import ru.javarush.country.dao.CountryDao;
import ru.javarush.country.dao.CountryHibernateDao;

@AllArgsConstructor
@Configuration
public class SpringConfiguration {

    private final SessionFactory sessionFactory;

    @Bean
    public CityDao cityDao() {
        return new CityHibernateDao(sessionFactory);
    }

    @Bean
    public CountryDao countryDao() {
        return new CountryHibernateDao(sessionFactory);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RedisClient redisClient() {
        return RedisClient.create(RedisURI.create("localhost", 6379));
    }
}
