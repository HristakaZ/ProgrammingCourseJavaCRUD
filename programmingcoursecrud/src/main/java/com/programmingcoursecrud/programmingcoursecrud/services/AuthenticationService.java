package com.programmingcoursecrud.programmingcoursecrud.services;


import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
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

    public String hashPassword(String password) {
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1,15*1024,2);

        return encoder.encode(password);
    }

    public boolean isPasswordMatchingHashedPassword(String password) {
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1,15*1024,2);
        String hashedPassword = hashPassword(password);

        return encoder.matches(password, hashedPassword);
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
