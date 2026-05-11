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

-- Ecuaciones rango 9
INSERT INTO ecuacion (num1, num2, num3, num4, resultado, rango)
VALUES
(3, 4, 2, 1, 13, 9),
(2, 5, 3, 1, 12, 9),
(1, 6, 4, 2, 8, 9),
(7, 2, 5, 4, 15, 9);

-- Ecuaciones rango 12
INSERT INTO ecuacion (num1, num2, num3, num4, resultado, rango)
VALUES 
(10, 2, 6, 4, 22, 12),
(12, 2, 9, 6, 27, 12),
(10, 4, 3, 1, 42, 12);

SELECT * FROM ecuacion;