package com.demo.manager.View.Interface;

/**
 * Created by Android on 2016/5/16.
 */
public interface Login extends MyError{



    String getName();//得到输入

    String getPassWord();//get

    void setUser(String id ,String name,String password);


}
