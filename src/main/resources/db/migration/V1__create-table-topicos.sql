  CREATE TABLE topicos (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      id_usuario VARCHAR(255) NOT NULL,
      titulo VARCHAR(255) NOT NULL UNIQUE,
      mensaje TEXT NOT NULL,  -- Eliminar UNIQUE o indexación de esta columna
      fecha_de_creacion DATETIME NOT NULL,
      autor VARCHAR(255) NOT NULL,
      status VARCHAR(50) NOT NULL,
      nombre_de_curso VARCHAR(255) NOT NULL,
      respuestas INT NOT NULL CHECK (respuestas >= 0)
  );
