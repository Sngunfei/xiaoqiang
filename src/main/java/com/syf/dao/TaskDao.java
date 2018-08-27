package com.syf.dao;

import com.syf.bean.Task;
import com.syf.bean.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class TaskDao {

    private static Logger logger = Logger.getLogger(TaskDao.class);

    public List<Task> getTaskByAccount(String account){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.getCurrentSession();
        Transaction transaction = null;
        List<Task> tasks = null;

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("from User where account = ?");
            query.setParameter(0, account);
            List<User> users = query.list();
            if(users.isEmpty())
                throw new Exception("该用户不存在！");
            User user = users.get(0);
            query = session.createQuery("from Task where userID = ?");
            query.setParameter(0, user.getId());
            tasks = query.list();
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        return tasks;
    }

    public List<Task> getTasksById(int userID){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        List<Task> tasks = null;

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Task where userID = ?");
            query.setParameter(0, userID);
            tasks = query.list();
            transaction.commit();
        }catch (HibernateException e) {
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return tasks;
    }

    public List<Task> getAllTask(){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.getCurrentSession();
        Transaction transaction = null;
        List tasks = null;

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Task");
            tasks = query.list();
            transaction.commit();
        }catch (HibernateException e){
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return tasks;
    }

    public void addTask(Task task){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.getCurrentSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            session.save(task);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void deleteTask(int taskID){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.getCurrentSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
//            Query query = session.createQuery("delete from Task where id = ?");
//            query.setParameter(0, taskID);
//            query.executeUpdate();
            Task task = session.load(Task.class, taskID);
            session.delete(task);

            transaction.commit();
        }catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void updateTask(Task task){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            session.saveOrUpdate(task);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public Task getTask(int id){
        SessionFactory sf = DB.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        Task task = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Task where id = ?");
            query.setParameter(0, id);
            List<Task> tasks = query.list();
            if(!tasks.isEmpty())
                task = tasks.get(0);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

        return task;
    }


//    public long updateTaskInfo(int taskId, String message, long time){
//        JedisPool jedisPool = DB.getJedisPool();
//        long ans = 0;
//        try(Jedis jedis = jedisPool.getResource()){
//            ans = jedis.zadd(DB.SSDBKeyForUserTaskInfo(taskId), time, message);
//            logger.info("ssdb insert new task info success.");
//        }catch (Exception e){
//            logger.error("ssdb insert new task info failed.", e);
//            e.printStackTrace();
//        }
//        return ans;
//    }
//
//    public Set<Tuple> getTaskProcess(int taskId){
//        JedisPool jedisPool = DB.getJedisPool();
//        Jedis jedis = jedisPool.getResource();
//        Set<Tuple> infos = jedis.zrevrangeWithScores(DB.SSDBKeyForUserTaskInfo(taskId), 0, Integer.MAX_VALUE);
//        logger.info("get task info from ssdb.");
//        return infos;
//    }
}
