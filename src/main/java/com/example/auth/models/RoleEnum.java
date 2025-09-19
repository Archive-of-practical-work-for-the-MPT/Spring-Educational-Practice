package com.example.auth.models;

import org.springframework.security.core.GrantedAuthority;

// Перечисление ролей пользователей в системе океанариума
public enum RoleEnum implements GrantedAuthority {
    // Администратор - имеет полный доступ ко всем функциям системы
    ADMIN,
    
    // Сотрудник - может управлять животными, расписанием и билетами
    EMPLOYEE,
    
    // Посетитель - может просматривать информацию и покупать билеты
    VISITOR;

    @Override
    public String getAuthority() {
        return name();
    }
}