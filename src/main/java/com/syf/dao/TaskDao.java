package com.syf.dao;

import com.syf.bean.Task;
import com.syf.bean.User;
import com.syf.biz.logger;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Set;

@Repository
@Scope("singleton")
public class TaskDao {

    public List<Task> getTaskByAccount(String account){
        SessionFactory sf = DB.getSessionFactory();
        List<Task> tasks = null;
        try(Session session = sf.openSession()){
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Task where user = ?");
            query.setParameter(0, account);
            tasks = query.list();
            transaction.commit();
        }catch (HibernateException e){
            logger.error(String.format("TaskDao: getTaskByAccount failed. Account: %s", account), e);
            e.printStackTrace();
        }
        return tasks;
    }

    public List<Task> getAllTask(){
        SessionFactory sf = DB.getSessionFactory();
        List<Task> tasks = null;
        try(Session session = sf.openSession()){
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Task order by id desc");
            tasks = query.list();
            transaction.commit();
        }catch (HibernateException e){
            logger.error("TaskDao: getAllTask failed", e);
            e.printStackTrace();
        }
        return tasks;
    }

    public void save(Task task){
        SessionFactory sf = DB.getSessionFactory();
        Transaction transaction = null;
        try(Session session = sf.openSession()){
            transaction = session.beginTransaction();
            session.save(task);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            logger.error("TaskDao: save task failed", e);
            e.printStackTrace();
        }
    }

    public void delete(Task task){
        delete(task.getID());
    }

    public void delete(int taskID){
        SessionFactory sf = DB.getSessionFactory();
        Transaction transaction = null;
        try(Session session = sf.openSession()){
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete Task where id = ?");
            query.setParameter(0, taskID).executeUpdate();
            transaction.commit();
            logger.info("TaskDao: delete task successfully.");
        }catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();
            logger.error("TaskDao: delete task failed.", e);
            e.printStackTrace();
        }
    }

    public void update(Task task){
        SessionFactory sf = DB.getSessionFactory();
        Transaction transaction = null;
        try(Session session = sf.openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(task);
            transaction.commit();
            logger.info("TaskDao: update task successfully.");
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            logger.error("TaskDao: update task failed.", e);
            e.printStackTrace();
        }
    }

    public Task get(int taskID){
        SessionFactory sf = DB.getSessionFactory();
        Transaction transaction = null;
        Task task = null;
        try(Session session = sf.openSession()){
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Task where id = ?");
            query.setParameter(0, taskID);
            List<Task> tasks = query.list();
            task = tasks.isEmpty() ? null : tasks.get(0);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            logger.error("TaskDao: get task failed.", e);
            e.printStackTrace();
        }
        return task;
    }

    public void addTaskProcess(int taskID, String message){
        JedisPool jedisPool = DB.getJedisPool();
        try(Jedis jedis = jedisPool.getResource()){
            jedis.zadd(DB.SSDBKeyForUserTaskInfo(taskID), System.currentTimeMillis(), message);
            logger.info("Insert task process info successfully.");
        }catch (Exception e){
            logger.error("Insert task process info failed.", e);
            e.printStackTrace();
        }
    }

    public Set<Tuple> getTaskProcess(int taskID){
        JedisPool jedisPool = DB.getJedisPool();
        Jedis jedis = jedisPool.getResource();
        return jedis.zrevrangeWithScores(DB.SSDBKeyForUserTaskInfo(taskID),
                0, Integer.MAX_VALUE);
    }

    public void clearTaskProcess(int taskID){
        JedisPool jedisPool = DB.getJedisPool();
        try(Jedis jedis = jedisPool.getResource()){
            jedis.zremrangeByScore(DB.SSDBKeyForUserTaskInfo(taskID), 0, Double.MAX_VALUE);
            logger.info(String.format("Clear task%s process info successfully.", taskID));
        }catch (Exception e){
            logger.error(String.format("Clear task%s process info failed.", taskID), e);
            e.printStackTrace();
        }
    }

    public List<Task> getTaskByStatus(String status){
        SessionFactory sf = DB.getSessionFactory();
        List<Task> tasks = null;
        try(Session session = sf.openSession()){
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Task where status = ? order by id desc")
                    .setParameter(0, status);
            tasks = query.list();
            transaction.commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return tasks;
    }

}
