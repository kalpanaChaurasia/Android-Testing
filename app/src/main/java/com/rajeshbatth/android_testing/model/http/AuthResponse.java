package com.rajeshbatth.android_testing.model.http;

import com.rajeshbatth.android_testing.model.User;

/**
 * Author: Rajesh Batth
 * Date: 20-Jun-2015.
 */
public class AuthResponse {

    private String error;

    private int errorCode;

    private User user;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
