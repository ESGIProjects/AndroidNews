package com.esgi.androtopic.Data.Model;

import io.realm.RealmObject;

/**
 * Created by kevin on 25/06/2017.
 */

public class User extends RealmObject {

    private String email;
    private String password;
    private String token;

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getToken(){
        return token;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setToken(String token){
        this.token = token;
    }

}
