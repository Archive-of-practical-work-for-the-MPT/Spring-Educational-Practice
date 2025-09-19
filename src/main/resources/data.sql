-- Создание таблицы пользователей
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

-- Создание таблицы ролей пользователей
CREATE TABLE user_role (
    user_id BIGINT NOT NULL,
    roles VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    UNIQUE(user_id, roles)
);

-- Создание таблиц для функционала океанариума
CREATE TABLE animals (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    species VARCHAR(100) NOT NULL,
    description TEXT,
    habitat VARCHAR(100)
);

CREATE TABLE shows (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    schedule_time TIME,
    duration INTEGER
);

CREATE TABLE tickets (
    id BIGSERIAL PRIMARY KEY,
    visitor_id BIGINT,
    show_id BIGINT,
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    price DECIMAL(10, 2),
    FOREIGN KEY (visitor_id) REFERENCES users(id),
    FOREIGN KEY (show_id) REFERENCES shows(id)
);

-- Добавление администратора по умолчанию
INSERT INTO users (username, password, active) VALUES 
('admin', '$2a$08$eUTCpi8dM.E0CovgDsJ0G.wQFJlGg5XDJGMQsYKQKJjzcq8T1qyjK', true);

-- Назначение роли администратора
INSERT INTO user_role (user_id, roles) VALUES 
(1, 'ADMIN');

-- Добавление тестовых данных для сотрудников
INSERT INTO users (username, password, active) VALUES 
('employee1', '$2a$08$eUTCpi8dM.E0CovgDsJ0G.wQFJlGg5XDJGMQsYKQKJjzcq8T1qyjK', true),
('employee2', '$2a$08$eUTCpi8dM.E0CovgDsJ0G.wQFJlGg5XDJGMQsYKQKJjzcq8T1qyjK', true);

-- Назначение ролей тестовым сотрудникам
INSERT INTO user_role (user_id, roles) VALUES 
(2, 'EMPLOYEE'),
(3, 'EMPLOYEE');

-- Добавление тестовых данных для посетителей
INSERT INTO users (username, password, active) VALUES 
('visitor1', '$2a$08$eUTCpi8dM.E0CovgDsJ0G.wQFJlGg5XDJGMQsYKQKJjzcq8T1qyjK', true),
('visitor2', '$2a$08$eUTCpi8dM.E0CovgDsJ0G.wQFJlGg5XDJGMQsYKQKJjzcq8T1qyjK', true),
('visitor3', '$2a$08$eUTCpi8dM.E0CovgDsJ0G.wQFJlGg5XDJGMQsYKQKJjzcq8T1qyjK', true);

-- Назначение ролей тестовым посетителям
INSERT INTO user_role (user_id, roles) VALUES 
(4, 'VISITOR'),
(5, 'VISITOR'),
(6, 'VISITOR');

-- Добавление тестовых данных для животных
INSERT INTO animals (name, species, description, habitat) VALUES 
('Флиппер', 'Дельфин', 'Игривый дельфин, любит прыгать через кольца. Обладает высоким интеллектом и дружелюбием к людям.', 'Дельфинарий'),
('Немо', 'Рыба клоун', 'Маленькая оранжевая рыбка с белыми полосками. Живет в симбиозе с актиниями.', 'Коралловый риф'),
('Шелли', 'Морская черепаха', 'Мудрая морская черепаха возрастом 100 лет. Путешествует по океану в поисках водорослей.', 'Бассейн с песком'),
('Оскар', 'Осьминог', 'Умный осьминог с восемью щупальцами. Может менять цвет кожи для маскировки.', 'Подводная пещера'),
('Марина', 'Морская звезда', 'Ярко-красная морская звезда с пятью лучами. Может восстанавливать утраченные части тела.', 'Дно океана'),
('Блу', 'Попугай Ара', 'Ярко-синий попугай с желтым клювом. Обладает отличной памятью и может повторять человеческую речь.', 'Тропический остров'),
('Луна', 'Медуза', 'Прозрачная медуза с длинными щупальцами. Плавает грациозно, подобно лунному свету.', 'Открытый океан'),
('Рокки', 'Морской лев', 'Игривый морской лев с густой гривой. Любит выступать на шоу и получать аплодисменты.', 'Бассейн морских львов'),
('Коралл', 'Рифовый окунь', 'Маленькая рыбка ярко-желтого цвета. Защищает свою территорию и помогает поддерживать экосистему рифа.', 'Коралловый риф'),
('Белла', 'Белуга', 'Белый кит с выразительными глазами. Известен своим разнообразным вокальным репертуаром.', 'Арктический бассейн');

-- Добавление тестовых данных для шоу
INSERT INTO shows (name, description, schedule_time, duration) VALUES 
('Великолепные дельфины', 'Захватывающее шоу с дельфинами, демонстрирующими свои способности прыгать через кольца и играть с мячами.', '10:00:00', 45),
('Подводное царство', 'Путешествие по миру коралловых рифов с живыми представителями морской фауны.', '11:30:00', 30),
('Танцы осьминогов', 'Необычайные танцы морских обитателей под музыку.', '13:15:00', 40),
('Морские львы в действии', 'Акробатические выступления морских львов с прыжками и ловкостью.', '14:30:00', 35),
('Мир медуз', 'Гипнотическое шоу с graceful медузами разных видов и цветов.', '15:45:00', 25),
('Попугаи и их таланты', 'Интерактивное шоу с попугаями, демонстрирующими свои способности к обучению.', '17:00:00', 30),
('Секреты морской черепахи', 'Образовательное шоу о жизни морских черепах и их миграции.', '18:15:00', 40),
('Ночь в океане', 'Специальное вечернее шоу с подсветкой и ночной фауной океана.', '19:30:00', 50),
('Коралловый сад', 'Показ разнообразия кораллов и рыбы, обитающих в коралловых рифах.', '20:45:00', 35),
('Аквариумные истории', 'Сказочные истории о жизни обитателей аквариума, рассказаные актерами.', '22:00:00', 45);

-- Добавление тестовых данных для билетов
INSERT INTO tickets (visitor_id, show_id, price) VALUES 
(4, 1, 500.00),
(4, 2, 500.00),
(5, 3, 500.00),
(5, 4, 500.00),
(6, 5, 500.00);

-- UPDATE user_role SET roles = 'EMPLOYEE' WHERE user_id = 7 AND roles = 'VISITOR';
-- select * from user_role

-- UPDATE user_role SET roles = 'ADMIN' WHERE user_id = 8 AND roles = 'VISITOR';
-- select * from user_role

-- select * from users