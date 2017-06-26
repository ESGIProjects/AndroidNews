package com.esgi.androtopic.Tools;

/**
 * Created by kevin on 25/06/2017.
 */

public class CheckRules {

    public static boolean isEmailValid(String email) {
        return email.contains("@");
    }

    public static boolean isPasswordValid(String password) {
        return password.length() >= 4;
    }

    public static boolean isFirstnameValid(String firstname){
        return (firstname.replaceAll("\\p{Z}","").length() > 0);
    }

    public static boolean isLastnameValid(String lastname){
        return (lastname.replaceAll("\\p{Z}","").length() > 0);
    }

}
