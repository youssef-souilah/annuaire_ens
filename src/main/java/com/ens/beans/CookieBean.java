package com.ens.beans;
import jakarta.servlet.http.Cookie;

public class CookieBean {
    private String role;

    public  String getCookie(Cookie[] cookies,String keyword) {
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(keyword)) {
                    role = cookie.getValue();
                    return role;
                }
            }           
        }
        return null;
    }

    
}

