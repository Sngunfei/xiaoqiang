package com.syf.dao;

import com.syf.biz.logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

class DB {
    private static SessionFactory sessionFactory;
    private static JedisPool jedisPool;

    static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    static JedisPool getJedisPool(){ return jedisPool; }

    private DB() {}

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        //openSSDB();
    }

    private static void openSSDB(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMaxTotal(20);
        Properties properties = new Properties();
        try{
            properties.load(new FileReader("ssdb.properties"));
        }catch (FileNotFoundException e) {
            logger.error("Can't find ssdb.properties.", e);
            e.printStackTrace();
        } catch (IOException e){
            logger.error("Can't load ssdb.properties.", e);
            e.printStackTrace();
        }
        jedisPool = new JedisPool(jedisPoolConfig,
                properties.getProperty("ip"),
                Integer.valueOf(properties.getProperty("port")));
    }

    static String SSDBKeyForUserTaskInfo(int taskId){
        return String.format("xq.task.process.%d", taskId);
    }
}
