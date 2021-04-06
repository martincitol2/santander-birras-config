package com.challenge.santander.domain.util;

public class Validations {

    public static boolean isAdmin(String userName){
        return userName.toLowerCase().contains("admin");
    }
}
