package com.demo.manager.Util;

import android.content.Context;
import android.util.Log;

import com.demo.manager.View.Interface.Login;
import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 引包RXJava1.1.3 RXAndroid1.1.0 OKHttp3.2.0 OKio1.7.0
 * 接口请求工具 现已添加get,post请求
 */
public class HttpApi<T> {
    public static final String ISEMPTY = "NULL";
    public static final String SUCCESS = "success";
    public static final String NETWORKLOST = "网络连接失败或未链接网络";
    private String url;
    private OkHttpClient client;
    private Observable obs;
    private Context context;

    /**
     * .subscribe(new Action1<Object>() {
     *
     * @Override public void call(Object o) {
     * if (o != null && !o.equals("")) {
     * my = (My) o;
     * arr=my.getArr();
     * myhandler.sendEmptyMessage(0);
     * setVisibility(VISIBLE);
     * invalidate();
     * }
     * }
     * }, new Action1<Throwable>() {
     * @Override public void call(Throwable throwable) {
     * <p/>
     * }
     * });
     */

    public HttpApi() {
        if (client == null) {
            client = new OkHttpClient();
        }
    }

    public HttpApi(Context context) {
        if (client == null) {
            client = new OkHttpClient();
        }
        this.context = context;
    }

    public Observable<String> httpGet(String string) {
        url = string;
        return allHttp(new Request.Builder().url(string).build());
    }

    /**
     * post Body封装 new FormBody.Builder().add("info_id","2").add("state","2").add("info","android文本添加").build();
     */
    public Observable<String> httpPost(String string, FormBody body) {
        url = string;
        return allHttp(new Request.Builder().post(body).url(string).build());
    }

    public Observable<String> allHttp(final Request request) {
        return Observable.just(url).observeOn(Schedulers.newThread()).map(new Func1<String, String>() {
            @Override
            public String call(String string) {
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.i("whx", "1");
                        return response.body().string();
                    } else {
                        Log.i("whx", "@");
                        return NETWORKLOST;
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage());
                }

            }
        });
    }


    /**
     * 后返回泛型
     */
    public Observable myHttpPost(String url,FormBody body){
        Request request= new Request.Builder().post(body).url(url).build();
        return myHttpAll(request);
    }
    public Observable myHttpGet(String url){
        Request request= new Request.Builder().url(url).build();
        return myHttpAll(request);
    }

    public Observable myHttpAll(final Request request){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override public void call(final Subscriber<? super String> subscriber) {
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        subscriber.onNext(response.body().string());
                    } else {
                        Log.i("error",response.body().string());
                        subscriber.onError(new Exception("error"));
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }



    /**
     *Observable.create(new Observable.OnSubscribe<String>() {
    @Override public void call(final Subscriber<? super String> subscriber) {
    try {
    String tgtPath = casServer + "/v1/tickets";
    FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
    formEncodingBuilder.add("username", account);
    formEncodingBuilder.add("password", password);
    RequestBody formBody = formEncodingBuilder.build();

    Request request = new Request.Builder()
    .url(tgtPath)
    .post(formBody)
    .build();
    Response response = mOkHttpClient.newCall(request).execute();
    int code = response.code();
    if (201 == code) {
    String location = response.header("Location");
    String tgt = location.substring(location.lastIndexOf("/") + 1);
    subscriber.onNext(tgt);
    } else {
    System.out.println(code);
    System.out.println(response.body().string());
    subscriber.onError(new Exception("error"));
    }
    } catch (Exception e) {
    subscriber.onError(e);
    }
    }
    });
     * */
}
