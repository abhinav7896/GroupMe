package com.csci5308.groupme.user.model;

import constants.Roles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserAuthDetailsTest {

    @Mock
    User user;

    @InjectMocks
    UserAuthDetails userAuthDetails;

    @SuppressWarnings("unchecked")
    @Test
    public void getAuthoritiesTest() {
        List<String> roles = new ArrayList<String>();
        roles.add(Roles.STUDENT);
        roles.add(Roles.TA);
        when(user.getRoles()).thenReturn(roles);
        Set<GrantedAuthority> authorities = (Set<GrantedAuthority>) userAuthDetails.getAuthorities();
        assertTrue(!authorities.isEmpty());
        assertEquals(2, authorities.size());
    }
}
