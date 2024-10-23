package com.davidsolli.dscommerce.services;

import com.davidsolli.dscommerce.entities.User;
import com.davidsolli.dscommerce.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public void validateSelfAdmin(Long userId) throws ForbiddenException {
        User me = userService.authenticated();
        if (!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)) {
            throw new ForbiddenException("Access denied!");
        }
    }
}
