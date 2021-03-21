package com.BoostingWebsite.utils;

import com.BoostingWebsite.account.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class ApplicationSession {
    private final HttpServletRequest request;

    private ApplicationContext context;

    public ApplicationSession(final HttpServletRequest request) {
        this.request = request;
    }

    public void setUser(UserDto user){
        context = context.getInstance();
        context.setUser(user);
    }

    public ApplicationContext getContext(){
        return context;
    }

    public String getAppUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append("http://").append(request.getServerName()).append(":").append(request.getServerPort()).append(request.getContextPath());
        return sb.toString();
    }

    public String getToken(){
        return UUID.randomUUID().toString();
    }
}