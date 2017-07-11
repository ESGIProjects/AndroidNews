package com.esgi.androtopic.Data.Model;

import io.realm.RealmObject;

/**
 * Created by kevin on 25/06/2017.
 */

public class User extends RealmObject {

    private String _id;
    private String email;
    private String password;
    private String token;
    private String firstname;
    private String lastname;

    public User(){

    }

    public User(String _id, String email, String password, String token, String firstname, String lastname){
        this._id = _id;
        this.email = email;
        this.password = password;
        this.token = token;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getId(){
        return _id;
    }

    public void setId(String id){
        this._id = id;
    }

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
