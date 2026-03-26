CREATE DATABASE IF NOT EXISTS ooodle;
USE ooodle;

CREATE TABLE IF NOT EXISTS  ecuacion (
    id INT AUTO_INCREMENT PRIMARY KEY,
    num1 INT NOT NULL,
    num2 INT NOT NULL,
    num3 INT NOT NULL,
    num4 INT NOT NULL,
    resultado INT NOT NULL,
    rango INT NOT NULL
);

INSERT INTO ecuacion (num1, num2, num3, num4, resultado, rango)
VALUES 
(3, 4, 2, 1, 13, 9),
(2, 5, 3, 1, 12, 9),
(1, 6, 4, 2, 8, 9),
(2, 8, 5, 3, 18, 12),
(3, 9, 4, 2, 29, 12);

USE ooodle; SELECT * FROM ecuacion;
