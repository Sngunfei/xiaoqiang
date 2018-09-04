package com.syf.biz;

import org.apache.log4j.Logger;

public class logger {
    private static Logger instance;

    public static Logger getInstance() {
        return instance;
    }

    private logger() {}

    static{
        instance = Logger.getLogger(logger.class);
    }

    public static void info(String msg){
        instance.info(msg);
    }

    public static void info(String msg, Throwable err){
        instance.info(msg, err);
    }

    public static void debug(String msg){
        instance.debug(msg);
    }

    public static void debug(String msg, Throwable err){
        instance.debug(msg, err);
    }

    public static void error(String msg){
        instance.error(msg);
    }

    public static void error(String msg, Throwable err){
        instance.error(msg, err);
    }
}
