package com.syf.dao;

import com.syf.bean.Address;
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
public class AddressDao {

    public Address getAddress(int addressID){
        SessionFactory sf = DB.getSessionFactory();
        Address address = null;
        try(Session session = sf.openSession()){
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Address where id = ?")
                    .setParameter(0, addressID);
            List<Address> addresses = query.list();
            address = addresses.isEmpty()? null: addresses.get(0);
            transaction.commit();
        }catch (HibernateException e){
            logger.error(String.format("AddressDao: get address%d failed", addressID), e);
            e.printStackTrace();
        }
        return address;
    }

    /**
     * @param des 地址详细信息, example: "北京市天安门"
     * @return 该地址的id
     */
    public Address getAddress(String des){
        SessionFactory sf = DB.getSessionFactory();
        Address address = null;
        try(Session session = sf.openSession()){
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Address where address = ?").
                    setParameter(0, des);
            List<Address> places = query.list();
            address = places.isEmpty() ? null: places.get(0);
            transaction.commit();
        }catch (HibernateException e){
            logger.error("AddressDao: get address failed. ", e);
            e.printStackTrace();
        }
        return address;
    }

    /**
     * @param place 新地址
     */
    public void save(Address place){
        SessionFactory sf = DB.getSessionFactory();
        Transaction transaction = null;
        try(Session session = sf.openSession()){
            transaction = session.beginTransaction();
            session.save(place);
            transaction.commit();
            logger.info("AddressDao: save new address successfully.");
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            logger.error("AddressDao: save new address failed.", e);
            e.printStackTrace();
        }
    }

    /**
     * @param address 地址更新
     */
    public void saveOrUpdate(Address address){
        SessionFactory sf = DB.getSessionFactory();
        Transaction transaction = null;
        try(Session session = sf.openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(address);
            transaction.commit();
            logger.info("AddressDao: save or update address successfully.");
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            logger.error("AddressDao: save or update address failed.", e);
            e.printStackTrace();
        }
    }

    /**
     * @param addressID 根据ID删除地址
     */
    public void delete(int addressID){
        SessionFactory sf = DB.getSessionFactory();
        Transaction transaction = null;
        try(Session session = sf.openSession()){
            transaction = session.beginTransaction();
            session.createQuery("delete Address where id = ?")
                    .setParameter(0, addressID).executeUpdate();
            transaction.commit();
            logger.info("AddressDao: delete address successfully.");
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            logger.error("AddressDao: delete address failed.", e);
            e.printStackTrace();
        }
    }

    public void delete(Address address){
        delete(address.getId());
    }

    /**
     * @return mysql中所有的地址
     */
    public List<Address> getAllAddress(){
        SessionFactory sf = DB.getSessionFactory();
        List<Address> places = null;

        try(Session session = sf.openSession()){
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Address");
            places = query.list();
            transaction.commit();
        }catch (HibernateException e){
            logger.error("AddressDao: get all addresses failed.", e);
            e.printStackTrace();
        }
        return places;
    }
}
