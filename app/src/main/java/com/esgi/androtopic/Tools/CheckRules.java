package com.esgi.androtopic.Tools;

import android.widget.EditText;

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
    public static boolean isNull(String text){
        return (text.length() == 0);
    }
    public static void clearField(EditText et){
        et.getText().clear();
    }
}
