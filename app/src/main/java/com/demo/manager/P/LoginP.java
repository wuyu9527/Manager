package com.demo.manager.P;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.AndroidException;
import android.util.Log;


import com.demo.manager.Bean.User;
import com.demo.manager.Model.UserM;
import com.demo.manager.Util.HttpApi;
import com.demo.manager.View.Interface.Login;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Android on 2016/5/16.
 */
public class LoginP {
    private Login login;//view 接口
    private UserM userM;//model 继承接口的类
    private Context context;//用到的activity

    public LoginP(Login l) {
        this.login = l;
        this.userM = new UserM();
    }

    public void saveUser(String name, String password) {//注册
        userM.setName(name);
        userM.setPaasWord(password);
        login.getName();
        login.getPassWord();

    }
    public void myLoadUser(String name, String passwrod) {
        userM.mylogin(name, passwrod)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {
                    @Override
                    public void onCompleted() {//订阅者终结调用
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(Object o) {
                        User user = new Gson().fromJson(o.toString(),User.class);
                        if (user!=null) {
                            login.setUser(user.getId(), user.getName(), user.getInKey());
                        }else {
                            try {
                                JSONObject obj =new JSONObject(o.toString());
                                if (o==null){
                                    login.setMyError("数据解析错误");
                                }
                                login.setMyError(obj.optString("err"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

    }



}
