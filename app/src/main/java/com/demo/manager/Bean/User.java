package com.demo.manager.Bean;

/**
 * Created by Android on 2016/5/16.
 */
public class User {

    public User() {
    }

    public User(String id, String name, String inKey) {
        this.id = id;
        this.name = name;
        InKey = inKey;
    }

    /**
     * id : 1
     * name : whx
     * InKey : e10adc3949ba59abbe56e057f20f883e
     */


    private String id;
    private String name;
    private String InKey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInKey() {
        return InKey;
    }

    public void setInKey(String InKey) {
        this.InKey = InKey;
    }
}
