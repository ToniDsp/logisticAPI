-- .  Muchos datos duplicados. Lo hablamos en al proxima clase:
CREATE TABLE productos
(
    id_producto     INT PRIMARY KEY,
    nombre_producto VARCHAR(20)
);
CREATE TABLE localizaciones
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
CREATE TABLE almacenes
(
    id_almacen     INT,
    nombre_almacen VARCHAR,
    direccion      VARCHAR
);
CREATE TABLE productos_almacenes
(
    id_almacen               INT REFERENCES almacenes (id_almacen),
    id_producto              INT REFERENCES productos (id_producto),
    stock                    INT,
    id_localizacion_FK       INT REFERENCES localizaciones (id_localizacion),
    id_localizacion_anterior INT REFERENCES localizaciones (id_localizacion),
    PRIMARY KEY (id_almacen, id_producto)
);
CREATE TABLE transportes
(
    id_transporte INT PRIMARY KEY,
    id_camion_FK  INT REFERENCES camiones (id_camion),
    id_almacen_FK INT REFERENCES almacenes (id_almacen),
    fecha_salida  DATE
);
CREATE TABLE detalles_transporte
(
    id_transporte  INT REFERENCES transportes (id_transporte),
    id_producto_FK INT REFERENCES productos (id_producto),
    cantidad       INT,
    PRIMARY KEY (id_operacion, id_producto_FK),
);