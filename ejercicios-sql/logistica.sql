-- .  Muchos datos duplicados. Lo hablamos en al proxima clase:
CREATE TABLE producto
(
    id_producto     INT PRIMARY KEY,
    nombre_producto VARCHAR(20)
);
CREATE TABLE localizacion
(
    id_localizacion INT PRIMARY KEY,
    zona            VARCHAR(20),
    altura          INT
);
CREATE TABLE camiones
(
    id_camion INT PRIMARY KEY,
    modelo    VARCHAR(20)
);
CREATE TABLE almacen
(
    id_almacen               INT,
    direccion_almacen        VARCHAR,
    id_producto_FK           INT,
    stock                    INT,
    id_localizacion_FK       INT,
    id_localizacion_anterior INT,
    PRIMARY KEY (id_almacen, id_producto_FK),
    FOREIGN KEY (id_producto_FK) REFERENCES producto (id_producto),
    FOREIGN KEY (id_localizacion_FK) REFERENCES localizacion (id_localizacion),
    FOREIGN KEY (id_localizacion_anterior) REFERENCES localizacion (id_localizacion)
);
CREATE TABLE transporte
(
    id_operacion   INT,
    id_producto_FK INT,
    cantidad       INT,
    fecha_salida   DATE,
    id_camion_FK   INT,
    id_almacen_FK  INT,
    PRIMARY KEY (id_operacion, id_producto_FK),
    FOREIGN KEY (id_producto_FK) REFERENCES producto (id_producto),
    FOREIGN KEY (id_camion_FK) REFERENCES camiones (id_camion),
    FOREIGN KEY (id_almacen_FK, id_producto_FK) REFERENCES almacen (id_almacen, id_producto_FK)
);