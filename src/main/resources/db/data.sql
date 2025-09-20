-- Скрипт заполнения таблиц тестовыми данными

-- Заполнение таблицы пользователей
INSERT INTO users (username, password, role) VALUES
('admin', '$2a$10$w9ziB9J0EG7yB9B4bS5W8eO5D6V7p8q9r0s1t2u3v4w5x6y7z8a9b', 'admin'),
('ivanov', '$2a$10$w9ziB9J0EG7yB9B4bS5W8eO5D6V7p8q9r0s1t2u3v4w5x6y7z8a9b', 'employee'),
('petrov', '$2a$10$w9ziB9J0EG7yB9B4bS5W8eO5D6V7p8q9r0s1t2u3v4w5x6y7z8a9b', 'employee'),
('sidorov', '$2a$10$w9ziB9J0EG7yB9B4bS5W8eO5D6V7p8q9r0s1t2u3v4w5x6y7z8a9b', 'passenger'),
('smirnov', '$2a$10$w9ziB9J0EG7yB9B4bS5W8eO5D6V7p8q9r0s1t2u3v4w5x6y7z8a9b', 'passenger');

-- Заполнение таблицы должностей
INSERT INTO positions (name, description) VALUES
('Пилот', 'Управляет самолетом'),
('Бортпроводник', 'Обслуживает пассажиров на борту'),
('Диспетчер', 'Контролирует полеты'),
('Механик', 'Обслуживает и ремонтирует самолеты'),
('Администратор', 'Управляет персоналом и расписанием');

-- Заполнение таблицы сотрудников
INSERT INTO employees (first_name, last_name, email, birth_date, position_id, phone) VALUES
('Иван', 'Иванов', 'ivanov@avia.ru', '1985-03-15', 1, '+7(999)123-45-67'),
('Петр', 'Петров', 'petrov@avia.ru', '1990-07-22', 2, '+7(999)234-56-78'),
('Сергей', 'Сидоров', 'sidorov@avia.ru', '1988-11-30', 3, '+7(999)345-67-89'),
('Андрей', 'Смирнов', 'smirnov@avia.ru', '1992-01-10', 4, '+7(999)456-78-90'),
('Алексей', 'Кузнецов', 'kuznetsov@avia.ru', '1987-09-05', 5, '+7(999)567-89-01');

-- Заполнение таблицы пассажиров
INSERT INTO passengers (first_name, last_name, passport_number, birth_date, email, phone) VALUES
('Александр', 'Васильев', 'P12345678', '1995-05-20', 'vasilev@mail.ru', '+7(999)111-22-33'),
('Елена', 'Попова', 'P23456789', '1998-12-10', 'popova@gmail.com', '+7(999)222-33-44'),
('Дмитрий', 'Лебедев', 'P34567890', '1990-08-15', 'lebedev@yandex.ru', '+7(999)333-44-55'),
('Ольга', 'Козлова', 'P45678901', '2000-02-28', 'kozlova@mail.ru', '+7(999)444-55-66'),
('Михаил', 'Новиков', 'P56789012', '1993-07-12', 'novikov@gmail.com', '+7(999)555-66-77');

-- Заполнение таблицы самолетов
INSERT INTO aircrafts (model, serial_number, capacity, manufacturer) VALUES
('Boeing 737', 'B737-001', 180, 'Boeing'),
('Airbus A320', 'A320-001', 150, 'Airbus'),
('Boeing 777', 'B777-001', 300, 'Boeing'),
('Airbus A350', 'A350-001', 250, 'Airbus'),
('Sukhoi Superjet 100', 'SSJ100-001', 100, 'Sukhoi');

-- Заполнение таблицы аэропортов
INSERT INTO airports (name, code, city, country) VALUES
('Шереметьево', 'SVO', 'Москва', 'Россия'),
('Пулково', 'LED', 'Санкт-Петербург', 'Россия'),
('Домодедово', 'DME', 'Москва', 'Россия'),
('Толмачёво', 'OVB', 'Новосибирск', 'Россия'),
('Казань', 'KZN', 'Казань', 'Россия');

-- Заполнение таблицы рейсов
INSERT INTO flights (flight_number, departure_airport_id, arrival_airport_id, aircraft_id, departure_time, arrival_time) VALUES
('SU101', 1, 2, 1, '2025-09-21 08:00:00', '2025-09-21 09:30:00'),
('SU102', 2, 1, 1, '2025-09-21 10:00:00', '2025-09-21 11:30:00'),
('SU201', 1, 3, 2, '2025-09-21 12:00:00', '2025-09-21 13:30:00'),
('SU202', 3, 1, 2, '2025-09-21 14:00:00', '2025-09-21 15:30:00'),
('SU301', 1, 4, 3, '2025-09-21 16:00:00', '2025-09-21 19:00:00'),
('SU302', 4, 1, 3, '2025-09-21 20:00:00', '2025-09-21 23:00:00');

-- Заполнение таблицы бронирований
INSERT INTO bookings (booking_number, passenger_id, flight_id, booking_date, price, status) VALUES
('BR1001', 1, 1, '2025-09-20 10:00:00', 5000.00, 'confirmed'),
('BR1002', 2, 1, '2025-09-20 11:00:00', 5000.00, 'confirmed'),
('BR1003', 3, 2, '2025-09-20 12:00:00', 4500.00, 'pending'),
('BR1004', 4, 3, '2025-09-20 13:00:00', 6000.00, 'confirmed'),
('BR1005', 5, 4, '2025-09-20 14:00:00', 5500.00, 'cancelled');