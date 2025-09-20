-- Скрипт создания таблиц базы данных для авиакомпании

-- Создание таблицы пользователей
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Создание таблицы должностей
CREATE TABLE positions (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(200)
);

-- Создание таблицы сотрудников
CREATE TABLE employees (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    birth_date DATE NOT NULL,
    position_id BIGINT REFERENCES positions(id),
    phone VARCHAR(20) NOT NULL
);

-- Создание таблицы пассажиров
CREATE TABLE passengers (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    passport_number VARCHAR(20) NOT NULL UNIQUE,
    birth_date DATE NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL
);

-- Создание таблицы самолетов
CREATE TABLE aircrafts (
    id BIGSERIAL PRIMARY KEY,
    model VARCHAR(50) NOT NULL,
    serial_number VARCHAR(20) NOT NULL UNIQUE,
    capacity INTEGER NOT NULL,
    manufacturer VARCHAR(50) NOT NULL
);

-- Создание таблицы аэропортов
CREATE TABLE airports (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    code VARCHAR(3) NOT NULL UNIQUE,
    city VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL
);

-- Создание таблицы рейсов
CREATE TABLE flights (
    id BIGSERIAL PRIMARY KEY,
    flight_number VARCHAR(10) NOT NULL UNIQUE,
    departure_airport_id BIGINT NOT NULL REFERENCES airports(id),
    arrival_airport_id BIGINT NOT NULL REFERENCES airports(id),
    aircraft_id BIGINT NOT NULL REFERENCES aircrafts(id),
    departure_time TIMESTAMP NOT NULL,
    arrival_time TIMESTAMP NOT NULL
);

-- Создание таблицы бронирований
CREATE TABLE bookings (
    id BIGSERIAL PRIMARY KEY,
    booking_number VARCHAR(20) NOT NULL UNIQUE,
    passenger_id BIGINT NOT NULL REFERENCES passengers(id),
    flight_id BIGINT NOT NULL REFERENCES flights(id),
    booking_date TIMESTAMP NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL
);