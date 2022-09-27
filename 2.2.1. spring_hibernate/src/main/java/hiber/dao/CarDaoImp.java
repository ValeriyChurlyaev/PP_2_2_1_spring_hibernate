package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> carLsit() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    @Override
    public User getUserByCar(String model, int series) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Car WHERE model=:model and series=:series");
        query.setParameter("model", model);
        query.setParameter("series", series);
        Car car = (Car) query.getSingleResult();
        Query query1 = sessionFactory.getCurrentSession().createQuery("FROM User WHERE car=:car");
        query1.setParameter("car", car);
        User user = (User) query1.getSingleResult();
        return user;
    }

    ;


}
