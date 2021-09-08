package com.web.eneity;

public class User {
    private String id;
    private String password;
    private String name;
    private int type;

    public User(){
    }

    public User(String id, String name, int type){
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public User(String id, String password){
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
