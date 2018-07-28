package com.syf.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DB {

    private static SessionFactory sessionFactory;
    private static JedisPool jedisPool;

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static JedisPool getJedisPool(){
        return jedisPool;
    }


    private DB() {}

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(5);
        jedisPoolConfig.setMaxTotal(20);
        Properties ssdbProperties = new Properties();
        try{
            ssdbProperties.load(new FileReader("SSDB.properties"));
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        jedisPool = new JedisPool(jedisPoolConfig,
                                    ssdbProperties.getProperty("ssdb_write_ip"),
                                    Integer.valueOf(ssdbProperties.getProperty("ssdb_write_port")));
    }

    public static String SSDBKeyForUserTaskInfo(int taskId){
        return String.format("xiaoqiang.task.%d", taskId);
    }


}
