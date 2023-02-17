package ru.javarush.country.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.javarush.country.entity.City;
import ru.javarush.country.entity.Country;
import ru.javarush.country.entity.CountryLanguage;

import java.util.Properties;

@org.springframework.context.annotation.Configuration
public class HibernateConfiguration {

    private static HibernateConfiguration instance;
    private final SessionFactory sessionFactory;

    public HibernateConfiguration() {
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
            instance = new HibernateConfiguration();
        }
        return instance.sessionFactory;
    }
}
