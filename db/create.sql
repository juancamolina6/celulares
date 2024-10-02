CREATE DATABASE celulares;

CREATE TABLE celulares (
    id INT AUTO_INCREMENT PRIMARY KEY,      -- Llave primaria
    procesador VARCHAR(50),                 -- Procesador del celular
    marca VARCHAR(50),                      -- Marca del celular
    tipo_pantalla VARCHAR(50),              -- Tipo de pantalla (LCD, OLED, etc.)
    ram INT,                                -- Cantidad de memoria RAM en GB
    modelo VARCHAR(50),                     -- Modelo del celular
    cantidad_camaras TINYINT,               -- Número de cámaras del celular
    almacenamiento INT,                     -- Capacidad de almacenamiento en GB (opcional)
    fecha_lanzamiento DATE                  -- Fecha de lanzamiento (opcional)
    bateria INT,                            -- Tamaño de la bateria 
    os VARCHAR(30);                         -- Sistemna operativo del celular 
);
