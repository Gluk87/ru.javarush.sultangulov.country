package ru.javarush.country.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.javarush.country.configuration.AppSessionFactory;
import ru.javarush.country.entity.City;

import java.util.List;
import java.util.Optional;

public class CityDao {

    public List<City> getItems(int offset, int limit) {
        try(Session session = AppSessionFactory.getSessionFactory().openSession()) {
            Query<City> query = session
                    .createQuery("select c from City c", City.class)
                    .setFirstResult(offset)
                    .setMaxResults(limit);
            return query.list();
        }
    }

    public int getTotalCount() {
        try(Session session = AppSessionFactory.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("select count(c) from City c", Long.class);
            return Math.toIntExact(query.uniqueResult());
        }
    }

    public Optional<City> getById(Integer id) {
        try(Session session = AppSessionFactory.getSessionFactory().openSession()) {
            Query<City> query = session
                    .createQuery("select c from City c join fetch c.country where c.id = :ID", City.class)
                    .setParameter("ID", id);
            return Optional.ofNullable(query.getSingleResult());
        }
    }
}
