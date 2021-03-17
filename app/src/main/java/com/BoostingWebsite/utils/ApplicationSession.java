package com.BoostingWebsite.utils;

import com.BoostingWebsite.account.UserBusiness;
import com.BoostingWebsite.account.UserDto;
import com.BoostingWebsite.account.RoleName;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class ApplicationSession {
    private final UserBusiness userBusiness;
    private final HttpServletRequest request;

    private ApplicationContext context;

    public ApplicationSession(final UserBusiness userBusiness, final HttpServletRequest request) {
        this.userBusiness = userBusiness;
        this.request = request;
    }

    public void setUser(UserDto user){
        context = new ApplicationContext(user);
    }

    public ApplicationContext getContext(){
        return context;
    }

    public RoleName getCurrentUserRole(UserDto userDto) {
        return userBusiness.getRoleName(userDto);
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