package ru.javarush.country.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.javarush.country.configuration.AppSessionFactory;
import ru.javarush.country.entity.Country;

import java.util.List;

public class CountryDao {

    public List<Country> getAll(Integer maxItems) {
        try(Session session = AppSessionFactory.getSessionFactory().openSession()) {
            Query<Country> query = session
                    .createQuery("select c from Country c", Country.class)
                    .setMaxResults(maxItems);
            return query.list();
        }
    }
}
