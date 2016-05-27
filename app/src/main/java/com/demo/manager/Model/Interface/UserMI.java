package com.demo.manager.Model.Interface;


import rx.Observable;


/**
 * Created by Android on 2016/5/16.
 */
public interface UserMI {


    void setName(String name);

    void setPaasWord(String passWord);


    Observable mylogin(String name, String password);
}
