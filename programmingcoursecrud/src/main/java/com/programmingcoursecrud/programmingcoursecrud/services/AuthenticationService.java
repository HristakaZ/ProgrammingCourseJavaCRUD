package com.programmingcoursecrud.programmingcoursecrud.services;


import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AuthenticationService {
    private HttpSession httpSession;

    public AuthenticationService(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public boolean isAuthenticated() {
        if(httpSession.getAttribute("userEmail") != null) {
            return true;
        }

        return false;
    }

    public void login(String userEmail) {
        httpSession.setAttribute("userEmail", userEmail);
    }

    public void logout() {
        httpSession.setAttribute("userEmail", null);
    }

    public String getAuthenticatedUserEmail() {
        if(httpSession.getAttribute("userEmail") != null) {
            return httpSession.getAttribute("userEmail").toString();
        }

        return "";
    }
}
