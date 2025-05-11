-- Insertar profesores si no existen
INSERT INTO profesor (name)
VALUES ('Carlos Pérez'),
       ('María Rodríguez') ON DUPLICATE KEY
UPDATE name =
VALUES (name);

-- Insertar materias si no existen
INSERT INTO materia (name, profesor_id)
VALUES ('Matemáticas', 1),
       ('Física', 2) ON DUPLICATE KEY
UPDATE name =
VALUES (name);

-- Insertar estudiantes si no existen
INSERT INTO estudiante (name)
VALUES ('Juan Gómez'),
       ('Ana López') ON DUPLICATE KEY
UPDATE name =
VALUES (name);

-- Relacionar estudiantes con materias
INSERT INTO estudiante_materia (estudiante_id, materia_id)
VALUES (1, 1),
       (1, 2),
       (2, 1) ON DUPLICATE KEY
UPDATE estudiante_id =
VALUES (estudiante_id);
