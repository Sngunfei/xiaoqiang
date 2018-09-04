package com.syf.dao;

import com.syf.bean.User;
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
public class UserDao {

    public void save(User user){
        SessionFactory sf = DB.getSessionFactory();
        Transaction transaction = null;
        try(Session session = sf.openSession()){
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
    }

    public User get(String account){
        SessionFactory sf = DB.getSessionFactory();
        User user = null;
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from User where account = ?");
            query.setParameter(0, account);
            List users = query.list();
            user = users.isEmpty() ? null: (User) users.get(0);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void delete(String account){
        SessionFactory sf = DB.getSessionFactory();
        Transaction transaction = null;
        try(Session session = sf.openSession()){
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from User where account = ?");
            query.setParameter(0, account);
            query.executeUpdate();
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
    }

    public void update(User user){
        SessionFactory sf = DB.getSessionFactory();
        Transaction transaction = null;

        if(get(user.getAccount()) == null) {
            return;
        }

        try(Session session = sf.openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<User> getAllAccounts(){
        SessionFactory sf = DB.getSessionFactory();
        Transaction transaction = null;
        List<User> users = null;
        try(Session session = sf.openSession()){
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User");
            users = query.list();
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
        return users;
    }
}
