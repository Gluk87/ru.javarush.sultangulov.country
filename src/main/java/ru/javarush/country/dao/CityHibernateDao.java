package ru.javarush.country.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.country.entity.City;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CityHibernateDao implements CityDao {

    private final SessionFactory sessionFactory;

    @Override
    public List<City> getAll(int offset, int limit) {
        try(Session session = sessionFactory.openSession()) {
            Query<City> query = session
                    .createQuery("select c from City c", City.class)
                    .setFirstResult(offset)
                    .setMaxResults(limit);
            return query.list();
        }
    }

    @Override
    public int getTotalCount() {
        try(Session session = sessionFactory.openSession()) {
            Query<Long> query = session
                    .createQuery("select count(c) from City c", Long.class);
            return Math.toIntExact(query.uniqueResult());
        }
    }

    @Override
    public Optional<City> getById(Integer id) {
        try(Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("select c from City c join fetch c.country where c.id = :ID", City.class)
                    .setParameter("ID", id)
                    .setMaxResults(1)
                    .uniqueResultOptional();
        }
    }
}
