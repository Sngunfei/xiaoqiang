package com.syf.dao;

import com.syf.bean.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class UserDao {

    public void save(User user){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

//    public String getAccountById(int id){
//        SessionFactory sf = DB.getSessionFactory();
//        Session session = sf.openSession();
//        Transaction transaction = null;
//        String account = null;
//
//        try{
//            transaction = session.beginTransaction();
//            Query query = session.createQuery("from User where id = ?");
//            query.setParameter(0, id);
//            List<User> users = query.list();
//            account = users.isEmpty()? null: users.get(0).getAccount();
//            transaction.commit();
//        }catch (HibernateException e){
//            if(transaction != null)
//                transaction.rollback();
//            e.printStackTrace();
//        }finally {
//            session.close();
//        }
//
//        return account;
//    }

    public User getUserByAccount(String account){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        User user = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User where account = ?");
            query.setParameter(0, account);
            List<User> users = query.list();
            if(!users.isEmpty())
                user = users.get(0);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return user;
    }

    public User getUserById(int userId){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        User user = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User where id = ?");
            query.setParameter(0, userId);
            List<User> users = query.list();
            if(!users.isEmpty())
                user = users.get(0);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return user;
    }

    public void deleteUser(String account){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from User where account = ?");
            query.setParameter(0, account);
            query.executeUpdate();
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void updateUser(User theUer){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;

        // 账务已存在，无法更新
        if(this.getUserByAccount(theUer.getAccount()) == null)
            return;

        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(theUer);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

    }

    public List<User> getAllAccounts(){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        List<User> users = null;

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User");
            users = query.list();
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return users;
    }
}
