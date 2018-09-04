package com.syf.dao;

import com.syf.Const.Status;
import com.syf.bean.Car;
import com.syf.biz.logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope("singleton")
public class CarDao {

    public void save(Car car){
        SessionFactory sf = DB.getSessionFactory();
        Transaction transaction = null;
        try(Session session = sf.openSession()){
            transaction = session.beginTransaction();
            session.save(car);
            transaction.commit();
            logger.info("CarDao: add new car successfully.");
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            logger.error("CarDao: add new car failed.", e);
            e.printStackTrace();
        }
    }

    public void update(Car car){
        SessionFactory sf = DB.getSessionFactory();
        Transaction transaction = null;
        try(Session session = sf.openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(car);
            transaction.commit();
            logger.info("CarDao: update car info successfully.");
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            logger.error("CarDao: update car info failed.", e);
            e.printStackTrace();
        }
    }

    public Car get(int carID){
        SessionFactory sf = DB.getSessionFactory();
        Car car = null;
        try(Session session = sf.openSession()){
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Car where id = ?")
                    .setParameter(0, carID);
            List<Car> cars = query.list();
            car = cars.isEmpty()? null: cars.get(0);
            transaction.commit();
        }catch (HibernateException e){

            e.printStackTrace();
        }
        return car;
    }

    public Car getOne(){
        SessionFactory sf = DB.getSessionFactory();
        Car car = null;
        try(Session session = sf.openSession()){
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Car where status = ?")
                    .setParameter(0, Status.Ready.content);
            query.setMaxResults(1);
            List<Car> cars = query.list();
            car = cars.isEmpty()? null: cars.get(0);
            transaction.commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return car;
    }

    public List<Car> getAllCar(){
        SessionFactory sf = DB.getSessionFactory();
        List<Car> cars = null;
        try(Session session = sf.openSession()){
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Car order by id desc");
            cars = query.list();
            transaction.commit();
        }catch (HibernateException e){
            logger.error("CarDao: get all cars failed.", e);
            e.printStackTrace();
        }
        return cars;
    }

    public void delete(Car car){
        delete(car.getID());
    }

    public void delete(int carID){
        SessionFactory sf = DB.getSessionFactory();
        Transaction transaction = null;
        try(Session session = sf.openSession()){
            transaction = session.beginTransaction();
            session.createQuery("delete Car where id = ?")
                    .setParameter(0, carID)
                    .executeUpdate();
            transaction.commit();
            logger.info(String.format("CarDao: delete car%d successfully.", carID));
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            logger.error(String.format("CarDao: delete car%d failed.", carID), e);
            e.printStackTrace();
        }
    }

    public Car getCarByIp(String ip){
        SessionFactory sf = DB.getSessionFactory();
        Car car = null;
        try(Session session = sf.openSession()){
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Car where ip = ?")
                    .setParameter(0, ip);
            List<Car> cars = query.list();
            car = cars.isEmpty() ? null: cars.get(0);
            transaction.commit();
        }catch (HibernateException e){
            logger.error(String.format("CarDao: get car by ip:%s failed", ip), e);
            e.printStackTrace();
        }
        return car;
    }
}
