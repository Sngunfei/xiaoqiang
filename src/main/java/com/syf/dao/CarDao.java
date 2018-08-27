package com.syf.dao;

import com.syf.bean.Car;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CarDao {

    public void addCar(Car car){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            session.save(car);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void updateCar(Car car){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(car);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public Car getCarById(int carId){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        Car car = null;

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Car where carID = ?");
            query.setParameter(0, carId);
            List<Car> cars = query.list();
            car = cars.isEmpty()? null: cars.get(0);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            return car;
        }
    }

    public List<Car> getAllcar(){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        List<Car> cars = null;

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Car");
            cars = query.list();
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return cars;
    }

    public void deleteCar(int carID){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            Car car = session.load(Car.class, carID);
            session.delete(car);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public Car getCarByIp(String ip){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        Car car = null;

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Car where ip = ?");
            query.setParameter(0, ip);
            List<Car> cars = query.list();
            car = cars.isEmpty()? null: cars.get(0);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return car;
    }
}
