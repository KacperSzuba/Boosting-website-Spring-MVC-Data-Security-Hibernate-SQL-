package com.BoostingWebsite.utils;

import com.BoostingWebsite.account.UserFacade;
import com.BoostingWebsite.account.UserDto;
import com.BoostingWebsite.account.RoleName;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Component
public class ApplicationSession {
    private final UserFacade userFacade;
    private final HttpServletRequest request;

    public ApplicationSession(final UserFacade userFacade, final HttpServletRequest request) {
        this.userFacade = userFacade;
        this.request = request;
    }

    public UserDto getActualUser() {
        Principal principal = this.request.getUserPrincipal();
        return userFacade.findUserDtoByUsername(principal.getName());
    }

    public RoleName getCurrentUserRole(UserDto userDto) {
        return userFacade.getRoleName(userDto);
    }

    public String getAppUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append("http://").append(request.getServerName()).append(":").append(request.getServerPort()).append(request.getContextPath());
        return sb.toString();
    }
}