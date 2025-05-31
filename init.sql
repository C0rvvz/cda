-- Crear base de datos si no existe
CREATE
DATABASE IF NOT EXISTS `cdabdd`;
USE
`cdabdd`;

-- Crear tabla de profesores
CREATE TABLE IF NOT EXISTS profesor
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    name
    VARCHAR
(
    100
) NOT NULL UNIQUE
    );

-- Crear tabla de materias
CREATE TABLE IF NOT EXISTS materia
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    name
    VARCHAR
(
    100
) NOT NULL,
    profesor_id INT,
    FOREIGN KEY
(
    profesor_id
) REFERENCES profesor
(
    id
),
    UNIQUE
(
    name,
    profesor_id
)
    );

-- Crear tabla de estudiantes
CREATE TABLE IF NOT EXISTS estudiante
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    name
    VARCHAR
(
    100
) NOT NULL UNIQUE
    );

-- Crear tabla de relación estudiante-materia
CREATE TABLE IF NOT EXISTS estudiante_materia
(
    estudiante_id
    INT,
    materia_id
    INT,
    PRIMARY
    KEY
(
    estudiante_id,
    materia_id
),
    FOREIGN KEY
(
    estudiante_id
) REFERENCES estudiante
(
    id
),
    FOREIGN KEY
(
    materia_id
) REFERENCES materia
(
    id
)
    );

-- Insertar profesores
INSERT INTO profesor (name)
VALUES ('Carlos Pérez'),
       ('María Rodríguez') ON DUPLICATE KEY
UPDATE name =
VALUES (name);

-- Insertar materias (usamos subquery para obtener el ID del profesor)
INSERT INTO materia (name, profesor_id)
VALUES ('Matemáticas', (SELECT id FROM profesor WHERE name = 'Carlos Pérez')),
       ('Física', (SELECT id FROM profesor WHERE name = 'María Rodríguez')) ON DUPLICATE KEY
UPDATE name =
VALUES (name);

-- Insertar estudiantes
INSERT INTO estudiante (name)
VALUES ('Juan Gómez'),
       ('Ana López') ON DUPLICATE KEY
UPDATE name =
VALUES (name);

-- Insertar relaciones estudiante-materia
INSERT INTO estudiante_materia (estudiante_id, materia_id)
VALUES ((SELECT id FROM estudiante WHERE name = 'Juan Gómez'), (SELECT id FROM materia WHERE name = 'Matemáticas')),
       ((SELECT id FROM estudiante WHERE name = 'Juan Gómez'), (SELECT id FROM materia WHERE name = 'Física')),
       ((SELECT id FROM estudiante WHERE name = 'Ana López'),
        (SELECT id FROM materia WHERE name = 'Matemáticas')) ON DUPLICATE KEY
UPDATE estudiante_id =
VALUES (estudiante_id), materia_id =
VALUES (materia_id);
