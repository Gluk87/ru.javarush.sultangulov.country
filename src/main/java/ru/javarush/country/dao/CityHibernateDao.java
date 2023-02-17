package ru.javarush.country.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.javarush.country.configuration.HibernateConfiguration;
import ru.javarush.country.entity.City;

import java.util.List;

public class CityHibernateDao implements CityDao {

    @Override
    public List<City> getAll(int offset, int limit) {
        try(Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<City> query = session
                    .createQuery("select c from City c", City.class)
                    .setFirstResult(offset)
                    .setMaxResults(limit);
            return query.list();
        }
    }

    @Override
    public int getTotalCount() {
        try(Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery("select count(c) from City c", Long.class);
            return Math.toIntExact(query.uniqueResult());
        }
    }

    @Override
    public City getById(Integer id) {
        try(Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            Query<City> query = session
                    .createQuery("select c from City c join fetch c.country where c.id = :ID", City.class)
                    .setParameter("ID", id);
            return query.getSingleResult();
        }
    }
}
