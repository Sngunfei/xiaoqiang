package com.syf.dao;

import com.syf.bean.Administer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdministerDao {

    public void save(Administer administer){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            session.save(administer);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void updateAdmin(Administer admin){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;

        // 账户不存在，无法更新
        if(this.getAdminByAccount(admin.getAccount()) == null)
            return;

        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(admin);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
          session.close();
        }
    }

    public Administer getAdminByAccount(String account) {
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.getTransaction();
        Administer administer = null;
        try{
            transaction.begin();
            Query query = session.createQuery("from Administer where account = ?");
            query.setParameter(0, account);
            List<Administer> admins = query.list();
            for(Administer admin : admins)
                administer = admin;
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return administer;
    }

    public void deleteAdmin(String account){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.getTransaction();

        try{
            transaction.begin();
            Query query = session.createQuery("delete from Administer where account = ?");
            query.setParameter(0, account);
            query.executeUpdate();
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
