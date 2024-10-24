-- Creación de tablas para el proyecto

-- Tabla usuario
CREATE TABLE IF NOT EXISTS usuario (
    email VARCHAR(255) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255),
    direccion TEXT,
    edad INT,
    genero VARCHAR(20),
    es_admin BOOLEAN NOT NULL
);

-- Tabla producto
CREATE TABLE IF NOT EXISTS producto (
    id_producto SERIAL PRIMARY KEY,
    descripcion TEXT,
    peso DECIMAL(10, 2),
    color VARCHAR(50),
    tipo VARCHAR(50),
    nombre VARCHAR(100) NOT NULL,
    req_refrigerar BOOLEAN
);

-- Tabla stock
CREATE TABLE IF NOT EXISTS stock (
    id_stock SERIAL PRIMARY KEY,
    numero INT NOT NULL CHECK (numero >= 0), 
    id_producto INT UNIQUE,
    CONSTRAINT fk_producto_stock FOREIGN KEY (id_producto) REFERENCES producto(id_producto) ON DELETE CASCADE
);


-- Tabla pedido
CREATE TABLE IF NOT EXISTS pedido (
    id_pedido SERIAL PRIMARY KEY,
    estado VARCHAR(50),
    fecha_creacion TIMESTAMP NOT NULL,
    email_usuario VARCHAR(255),--Relación N:1 con usuario
    CONSTRAINT fk_usuario_pedido FOREIGN KEY (email_usuario) REFERENCES usuario(email) ON DELETE SET NULL
);

-- Tabla pedido_producto (relación N:M entre pedido y producto)
CREATE TABLE IF NOT EXISTS pedido_producto (
    id_pedido INT,
    id_producto INT,
    PRIMARY KEY (id_pedido, id_producto),
    CONSTRAINT fk_pedido FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido) ON DELETE CASCADE,
    CONSTRAINT fk_producto FOREIGN KEY (id_producto) REFERENCES producto(id_producto) ON DELETE CASCADE
);