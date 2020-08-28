package com.csci5308.groupme.auth;

import com.csci5308.groupme.auth.config.LoginSuccessHandler;
import constants.Roles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class LoginSuccessHandlerTest {

    @Mock
    Authentication authentication;

    @InjectMocks
    LoginSuccessHandler loginSuccessHandler;

    @Test
    public void landingUrlTestForAdmin() {
        String adminLandingUrl = "";
        List<String> roles = new ArrayList<String>();
        roles.add(Roles.ADMIN);
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);
        });
        doReturn(authorities).when(authentication).getAuthorities();
        adminLandingUrl = loginSuccessHandler.decideUrl(authentication);
        assertEquals(LandingUri.ADMIN, adminLandingUrl);
    }

    @Test
    public void landingUrlTestForGuest() {
        String landingUrl = "";
        List<String> roles = new ArrayList<String>();
        roles.add(Roles.GUEST);
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);
        });
        doReturn(authorities).when(authentication).getAuthorities();
        landingUrl = loginSuccessHandler.decideUrl(authentication);
        assertEquals(LandingUri.GUEST, landingUrl);
    }

    @Test
    public void landingUrlTestForStudent() {
        String landingUrl = "";
        List<String> roles = new ArrayList<String>();
        roles.add(Roles.STUDENT);
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);
        });
        doReturn(authorities).when(authentication).getAuthorities();
        landingUrl = loginSuccessHandler.decideUrl(authentication);
        assertEquals(LandingUri.STUDENT, landingUrl);
    }

    @Test
    public void landingUrlTestForTA() {
        String landingUrl = "";
        List<String> roles = new ArrayList<String>();
        roles.add(Roles.TA);
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);
        });
        doReturn(authorities).when(authentication).getAuthorities();
        landingUrl = loginSuccessHandler.decideUrl(authentication);
        assertEquals(LandingUri.TA, landingUrl);
    }

    @Test
    public void landingUrlTestForInstructor() {
        String landingUrl = "";
        List<String> roles = new ArrayList<String>();
        roles.add(Roles.INSTRUCTOR);
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);
        });
        doReturn(authorities).when(authentication).getAuthorities();
        landingUrl = loginSuccessHandler.decideUrl(authentication);
        assertEquals(LandingUri.INSTRUCTOR, landingUrl);
    }

    @Test
    public void landingUrlTestForStudentTA() {
        String landingUrl = "";
        List<String> roles = new ArrayList<String>();
        roles.add(Roles.STUDENT);
        roles.add(Roles.TA);
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);
        });
        doReturn(authorities).when(authentication).getAuthorities();
        landingUrl = loginSuccessHandler.decideUrl(authentication);
        assertEquals(LandingUri.STU_TA, landingUrl);
    }

    @Test
    public void landingUrlTestForStudentInstructor() {
        String landingUrl = "";
        List<String> roles = new ArrayList<String>();
        roles.add(Roles.STUDENT);
        roles.add(Roles.INSTRUCTOR);
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);
        });
        doReturn(authorities).when(authentication).getAuthorities();
        landingUrl = loginSuccessHandler.decideUrl(authentication);
        assertEquals(LandingUri.STU_INST, landingUrl);
    }


    @Test
    public void landingUrlTestForTAInstructor() {
        String landingUrl = "";
        List<String> roles = new ArrayList<String>();
        roles.add(Roles.TA);
        roles.add(Roles.INSTRUCTOR);
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);
        });
        doReturn(authorities).when(authentication).getAuthorities();
        landingUrl = loginSuccessHandler.decideUrl(authentication);
        assertEquals(LandingUri.TA_INST, landingUrl);
    }

    @Test
    public void landingUrlTestForStudentTAInstructor() {
        String landingUrl = "";
        List<String> roles = new ArrayList<String>();
        roles.add(Roles.TA);
        roles.add(Roles.INSTRUCTOR);
        roles.add(Roles.STUDENT);
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);
        });
        doReturn(authorities).when(authentication).getAuthorities();
        landingUrl = loginSuccessHandler.decideUrl(authentication);
        assertEquals(LandingUri.STU_TA_INST, landingUrl);
    }

    @Test
    public void landingUrlTestForGuestOverride() {
        String landingUrl = "";
        List<String> roles = new ArrayList<String>();
        roles.add(Roles.TA);
        roles.add(Roles.INSTRUCTOR);
        roles.add(Roles.STUDENT);
        roles.add(Roles.GUEST);
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(role);
            authorities.add(authority);
        });
        doReturn(authorities).when(authentication).getAuthorities();
        landingUrl = loginSuccessHandler.decideUrl(authentication);
        assertEquals(LandingUri.STU_TA_INST, landingUrl);
    }


}
