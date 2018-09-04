package com.syf.service;

import com.syf.Const.Status;

class Utils {

    static boolean checkStatus(Status status){
        return checkStatus(status.content);
    }

    static boolean checkStatus(String status){
        return Status.Ready.equals(status) ||
                Status.Running.equals(status) ||
                Status.Done.equals(status) ||
                Status.Fail.equals(status);
    }

}
