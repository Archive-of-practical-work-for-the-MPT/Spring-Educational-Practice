package com.example.project2.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class RoleUtils {
    
    // Проверяет, имеет ли пользователь роль администратора
    public static boolean isAdmin(Authentication authentication) {
        return hasRole(authentication, "ADMIN");
    }
    
    // Проверяет, имеет ли пользователь роль сотрудника
    public static boolean isEmployee(Authentication authentication) {
        return hasRole(authentication, "EMPLOYEE");
    }
    
    // Проверяет, имеет ли пользователь роль пассажира
    public static boolean isPassenger(Authentication authentication) {
        return hasRole(authentication, "PASSENGER");
    }
    
    // Проверяет, имеет ли пользователь определенную роль
    private static boolean hasRole(Authentication authentication, String role) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roleWithPrefix = "ROLE_" + role;
        
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(roleWithPrefix)) {
                return true;
            }
        }
        
        return false;
    }
}