-- CREATE DATABASE student;

-- Таблица для связи один-к-одному (Person и Passport)
CREATE TABLE persons (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    age INTEGER CHECK (age >= 0 AND age <= 150)
);

CREATE TABLE passports (
    id SERIAL PRIMARY KEY,
    series VARCHAR(10) NOT NULL,
    number VARCHAR(10) NOT NULL,
    issue_date DATE NOT NULL,
    person_id INTEGER UNIQUE REFERENCES persons(id) ON DELETE CASCADE
);

-- Таблица для связи один-ко-многим (Author и Books)
CREATE TABLE authors (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE,
    biography TEXT
);

CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    publication_date DATE,
    author_id INTEGER REFERENCES authors(id) ON DELETE SET NULL
);

-- Таблицы для связи многие-ко-многим (Students и Courses)
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    enrollment_date DATE NOT NULL,
    gpa DECIMAL(3,2) CHECK (gpa >= 0.0 AND gpa <= 4.0)
);

CREATE TABLE courses (
    id SERIAL PRIMARY KEY,
    code VARCHAR(10) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    credits INTEGER CHECK (credits >= 0),
    description TEXT
);

CREATE TABLE student_courses (
    student_id INTEGER REFERENCES students(id) ON DELETE CASCADE,
    course_id INTEGER REFERENCES courses(id) ON DELETE CASCADE,
    enrollment_date DATE NOT NULL,
    grade DECIMAL(3,2) CHECK (grade >= 0.0 AND grade <= 5.0),
    PRIMARY KEY (student_id, course_id)
);

-- Дополнительная таблица для демонстрации связей (Departments)
CREATE TABLE departments (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    head VARCHAR(100),
    budget DECIMAL(15,2) CHECK (budget >= 0)
);

-- Добавление поля department_id в таблицу courses для связи один-ко-многим
ALTER TABLE courses ADD COLUMN department_id INTEGER REFERENCES departments(id) ON DELETE SET NULL;

-- Индексы для улучшения производительности
CREATE INDEX idx_persons_email ON persons(email);
CREATE INDEX idx_books_author_id ON books(author_id);
CREATE INDEX idx_courses_department_id ON courses(department_id);
CREATE INDEX idx_students_last_name ON students(last_name);
CREATE INDEX idx_courses_code ON courses(code);

-- Примеры данных для тестирования
INSERT INTO persons (name, email, age) VALUES 
('Иван Иванов', 'ivan@example.com', 25),
('Мария Петрова', 'maria@example.com', 30);

INSERT INTO passports (series, number, issue_date, person_id) VALUES 
('1234', '567890', '2020-01-15', 1),
('5678', '098765', '2019-05-20', 2);

INSERT INTO departments (name, head, budget) VALUES 
('Компьютерные науки', 'Доктор Смит', 500000.00),
('Математика', 'Профессор Джонсон', 300000.00);

INSERT INTO authors (first_name, last_name, birth_date, biography) VALUES 
('Лев', 'Толстой', '1828-09-09', 'Русский писатель, один из величайших в мировой литературе'),
('Федор', 'Достоевский', '1821-11-11', 'Русский писатель, мыслитель, философ и публицист');

INSERT INTO books (title, isbn, publication_date, author_id) VALUES 
('Война и мир', '978-5-17-081841-1', '1869-01-01', 1),
('Анна Каренина', '978-5-17-081842-8', '1878-01-01', 1),
('Преступление и наказание', '978-5-17-081843-5', '1866-01-01', 2);

INSERT INTO students (first_name, last_name, enrollment_date, gpa) VALUES 
('Алексей', 'Сидоров', '2023-09-01', 3.75),
('Елена', 'Козлова', '2023-09-01', 4.00);

INSERT INTO courses (code, name, credits, description, department_id) VALUES 
('CS101', 'Введение в программирование', 3, 'Основы программирования на Java', 1),
('CS201', 'Структуры данных', 4, 'Изучение алгоритмов и структур данных', 1),
('MATH101', 'Высшая математика', 4, 'Основы высшей математики', 2);

INSERT INTO student_courses (student_id, course_id, enrollment_date, grade) VALUES 
(1, 1, '2023-09-01', 4.5),
(1, 2, '2023-09-01', 3.8),
(2, 1, '2023-09-01', 5.0),
(2, 3, '2023-09-01', 4.2);