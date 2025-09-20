package com.example.project2.config;

import com.example.project2.util.RoleUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserRoleInterceptor());
    }

    public static class UserRoleInterceptor implements HandlerInterceptor {
        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, 
                              Object handler, ModelAndView modelAndView) throws Exception {
            if (modelAndView != null && modelAndView.hasView()) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                
                if (authentication != null && authentication.isAuthenticated() 
                    && !authentication.getPrincipal().equals("anonymousUser")) {
                    
                    modelAndView.addObject("username", authentication.getName());
                    
                    if (RoleUtils.isAdmin(authentication)) {
                        modelAndView.addObject("userRole", "Администратор");
                    } else if (RoleUtils.isEmployee(authentication)) {
                        modelAndView.addObject("userRole", "Сотрудник");
                    } else if (RoleUtils.isPassenger(authentication)) {
                        modelAndView.addObject("userRole", "Пассажир");
                    } else {
                        modelAndView.addObject("userRole", "Пользователь");
                    }
                }
            }
        }
    }
}