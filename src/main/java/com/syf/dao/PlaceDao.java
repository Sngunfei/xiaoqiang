package com.syf.dao;

import com.syf.bean.Administer;
import com.syf.bean.Place;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceDao {

    public boolean isExist(String place){
        return this.getPlaceByAddr(place) != null;
    }

    public Place getPlaceById(int id){
        SessionFactory sf = Utils.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        Place place = null;

        try{
            transaction = session.beginTransaction();
            place = session.load(Place.class, id);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return place;
    }

    public Place getPlaceByAddr(String descrip){
        SessionFactory sf = Utils.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        Place place = null;

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Place where description = ?");
            query.setParameter(0,descrip);
            List<Place> places = query.list();
            if(!places.isEmpty())
                place = places.get(0);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return place;
    }

    public int addrToId(String decrip){
        Place place = this.getPlaceByAddr(decrip);
        if(place != null)
            return place.getDesID();
        return -1;
    }

    public void save(Place place){
        SessionFactory sf = Utils.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            session.save(place);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public List<Place> getAllPlaces(){
        SessionFactory sf = Utils.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        List<Place> places = null;

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Place");
            places = query.list();
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return places;
    }
}
