# Океанариум - Система управления доступом

## Описание проекта

Это веб-приложение для управления доступом к системе океанариума с тремя уровнями ролей:
- Администратор (ADMIN)
- Сотрудник (EMPLOYEE)
- Посетитель (VISITOR)

## Функциональность

### Роли пользователей

1. **Администратор (ADMIN)**
   - Управление пользователями системы
   - Назначение ролей
   - Просмотр статистики

2. **Сотрудник (EMPLOYEE)**
   - Управление животными
   - Редактирование расписания
   - Управление билетами

3. **Посетитель (VISITOR)**
   - Просмотр экспонатов
   - Просмотр расписания шоу
   - Покупка билетов

### Безопасность

- Регистрация и авторизация пользователей
- Шифрование паролей (BCrypt)
- Валидация паролей (минимум 8 символов, заглавные/строчные буквы, цифры, специальные символы)
- Автоматический выход по таймауту (15 минут активности)

## Технические детали

### Технологии

- Java 17
- Spring Boot 3.5.5
- Spring Security
- Spring Data JPA
- Thymeleaf
- PostgreSQL
- Bootstrap 5

### Структура базы данных

База данных создается автоматически при запуске приложения. Скрипт инициализации находится в `src/main/resources/data.sql`.

### Тестовые пользователи

После первого запуска в системе будут доступны следующие пользователи:
- Администратор: admin / admin123!
- Сотрудник: employee1 / admin123!
- Посетитель: visitor1 / admin123!

## Установка и запуск

1. Убедитесь, что у вас установлены:
   - Java 17
   - PostgreSQL
   - IntelliJ IDEA или другая среда разработки

2. Создайте базу данных в PostgreSQL:
   ```sql
   CREATE DATABASE aquarium;
   ```

3. Обновите настройки подключения к базе данных в файле `src/main/resources/application.properties`

4. Соберите проект:
   ```bash
   ./gradlew build
   ```

5. Запустите приложение:
   ```bash
   ./gradlew bootRun
   ```

6. Откройте в браузере: http://localhost:8080

## Структура проекта

```
src/
├── main/
│   ├── java/
│   │   └── com/example/auth/
│   │       ├── AuthApplication.java
│   │       ├── config/
│   │       │   └── WebSecurityConfig.java
│   │       ├── controllers/
│   │       │   ├── AdminController.java
│   │       │   ├── EmployeeController.java
│   │       │   ├── LoginController.java
│   │       │   ├── MainController.java
│   │       │   ├── RegistrationController.java
│   │       │   └── VisitorController.java
│   │       ├── models/
│   │       │   ├── Animal.java
│   │       │   ├── RoleEnum.java
│   │       │   ├── Show.java
│   │       │   ├── Ticket.java
│   │       │   └── User.java
│   │       ├── repos/
│   │       │   ├── AnimalRepository.java
│   │       │   ├── ShowRepository.java
│   │       │   ├── TicketRepository.java
│   │       │   └── UserRepository.java
│   │       └── services/
│   │           ├── AnimalService.java
│   │           ├── ShowService.java
│   │           └── TicketService.java
│   └── resources/
│       ├── application.properties
│       ├── data.sql
│       └── templates/
│           ├── about.html
│           ├── index.html
│           ├── login.html
│           ├── regis.html
│           ├── blocks/
│           │   └── header.html
│           ├── admin/
│           │   ├── index.html
│           │   ├── user_detail.html
│           │   └── users.html
│           ├── employee/
│           │   ├── animals.html
│           │   ├── index.html
│           │   ├── schedule.html
│           │   └── tickets.html
│           └── visitor/
│               ├── exhibits.html
│               ├── index.html
│               ├── shows.html
│               └── tickets.html
```

## Разработка

Для внесения изменений в проект:

1. Склонируйте репозиторий
2. Откройте проект в вашей IDE
3. Внесите необходимые изменения
4. Запустите тесты
5. Соберите проект

## Лицензия

Этот проект является учебным и распространяется "как есть" без каких-либо гарантий.