package ru.javarush.country.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import ru.javarush.country.entity.City;
import ru.javarush.country.entity.Country;
import ru.javarush.country.entity.CountryLanguage;

import java.util.Properties;

@org.springframework.context.annotation.Configuration
public class AppSessionFactory {

    private static AppSessionFactory instance;
    private final SessionFactory sessionFactory;

    public AppSessionFactory() {
        Properties properties = new Properties();

        sessionFactory = new Configuration()
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(CountryLanguage.class)
                .addProperties(properties)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (instance == null) {
            instance = new AppSessionFactory();
        }
        return instance.sessionFactory;
    }
}
