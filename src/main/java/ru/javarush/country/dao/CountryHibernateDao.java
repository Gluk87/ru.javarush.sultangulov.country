package ru.javarush.country.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.country.entity.Country;

import java.util.List;

@AllArgsConstructor
public class CountryHibernateDao implements CountryDao {

    private final SessionFactory sessionFactory;

    @Override
    public List<Country> getAll(Integer maxItems) {
        try(Session session = sessionFactory.openSession()) {
            Query<Country> query = session
                    .createQuery("select c from Country c", Country.class)
                    .setMaxResults(maxItems);
            return query.list();
        }
    }
}
