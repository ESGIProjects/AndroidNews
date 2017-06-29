package com.esgi.androtopic.Data.Model;

/**
 * Created by kevin on 24/06/2017.
 */

public class PostAuth{

    private String email;
    private String password;

    public PostAuth(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }
}

