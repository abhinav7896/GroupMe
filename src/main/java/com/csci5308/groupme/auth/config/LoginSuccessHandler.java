package com.csci5308.groupme.auth.config;

import com.csci5308.groupme.auth.LandingUri;
import constants.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

    private String landingUrl;

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        landingUrl = decideUrl(authentication);
        if (response.isCommitted()) {
            return;
        }
        RedirectStrategy redirectStractegy = new DefaultRedirectStrategy();
        redirectStractegy.sendRedirect(request, response, landingUrl);
    }

    public String decideUrl(Authentication authentication) {
        String url = "";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<>();
        authorities.forEach(authority -> roles.add(authority.getAuthority()));
        if (roles.size() > 1) {
            roles.removeIf(role -> role.equals(Roles.GUEST));
        }
        if (roles.contains(Roles.ADMIN)) {
            url = LandingUri.ADMIN;
        } else if (roles.size() == 1 && roles.contains(Roles.GUEST)) {
            url = LandingUri.GUEST;
        } else if (roles.size() == 1 && roles.get(0).equals(Roles.STUDENT)) {
            url = LandingUri.STUDENT;
        } else if (roles.size() == 1 && roles.get(0).equals(Roles.TA)) {
            url = LandingUri.TA;
        } else if (roles.size() == 1 && roles.get(0).equals(Roles.INSTRUCTOR)) {
            url = LandingUri.INSTRUCTOR;
        } else if (roles.size() == 2 && roles.contains(Roles.STUDENT) && roles.contains(Roles.TA)) {
            url = LandingUri.STU_TA;
        } else if (roles.size() == 2 && roles.contains(Roles.STUDENT) && roles.contains(Roles.INSTRUCTOR)) {
            url = LandingUri.STU_INST;
        } else if (roles.size() == 2 && roles.contains(Roles.TA) && roles.contains(Roles.INSTRUCTOR)) {
            url = LandingUri.TA_INST;
        } else if (roles.contains(Roles.STUDENT) && roles.contains(Roles.TA) && roles.contains(Roles.INSTRUCTOR)) {
            url = LandingUri.STU_TA_INST;
        }
        roles.forEach(role -> logger.info(role));
        return url;
    }
}
