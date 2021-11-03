DROP TABLE IF EXISTS user_table;
DROP TABLE IF EXISTS role_table;

CREATE TABLE IF NOT EXISTS role_table
(
    role_ID SERIAL PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS user_table
(
    user_ID SERIAL PRIMARY KEY,
    login VARCHAR(20) NOT NULL UNIQUE,
    user_password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    role_ID INT NOT NULL,
    FOREIGN KEY (role_ID) REFERENCES role_table (role_ID)
);