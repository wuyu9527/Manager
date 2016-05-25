package com.demo.manager.Model;

import com.demo.manager.Bean.User;
import com.demo.manager.Model.Interface.UserMI;
import com.demo.manager.Bean.ControlCenter;
import com.demo.manager.Util.HttpApi;

import okhttp3.FormBody;
import rx.Observable;

/**
 * Created by Android on 2016/5/16.
 */
public class UserM implements UserMI {

    private String name;
    private String password;
    private User user = new User();

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPaasWord(String passWord) {
        this.password=passWord;
    }

    @Override
    public Observable mylogin(String name, String password) {
        HttpApi httpApi = new HttpApi();
        FormBody formBody=new FormBody.Builder().add("name", name).add("password", password).build();
        return httpApi.myHttpPost(ControlCenter.login,formBody);
    }


}
