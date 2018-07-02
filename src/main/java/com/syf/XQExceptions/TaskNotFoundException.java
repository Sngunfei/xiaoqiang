package com.syf.XQExceptions;

public class TaskNotFoundException extends Exception {

    public TaskNotFoundException(String errorInfo){
        super(errorInfo);
    }
}
