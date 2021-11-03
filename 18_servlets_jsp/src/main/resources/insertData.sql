INSERT INTO role_table VALUES
    (1, 'Admin'),
    (2, 'User');

INSERT INTO user_table VALUES
    (1, 'admin', 'password', 'admin@email', 'Admin', 'Tester', '2000-01-01', 1),
    (2, 'user', 'password', 'user@email', 'User', 'Tester', '2000-01-01', 2);

ALTER SEQUENCE user_table_user_id_seq RESTART WITH 3;