CREATE TABLE Usuarios
(
    id_usuario INT PRIMARY KEY,
    nombre     VARCHAR(50),
    telefono   VARCHAR(20)
);
CREATE TABLE Grupo
(
    id_grupo       INT PRIMARY KEY,
    fecha_creacion DATETIME
);
CREATE TABLE Usuario_Grupo
(
    id_grupo_FK   INT,
    id_usuario_FK INT,
    PRIMARY KEY (id_grupo_FK, id_usuario_FK),
    FOREIGN KEY (id_usuario_FK) REFERENCES Usuarios (id_usuario),
    FOREIGN KEY (id_grupo_FK) REFERENCES Grupo (id_grupo)
);

CREATE TABLE Mensajes
(
    id_mensaje         INT PRIMARY KEY,
    id_usuario_FK      INT,
    id_grupo_FK        INT NULLABLE,
    id_destinatario_FK INT NULLABLE,
    mensaje            VARCHAR(300),
    fecha_enviado      DATETIME,
    FOREIGN KEY (id_usuario_FK) REFERENCES Usuarios (id_usuario),
    FOREIGN KEY (id_grupo_FK) REFERENCES Grupo (id_grupo)
);

CREATE TABLE Mensajes_grupo
(
    id_mensaje    INT PRIMARY KEY,
    id_usuario_FK INT,
    id_grupo_FK   INT,
    mensaje       VARCHAR(300),
    fecha_enviado DATETIME,
    FOREIGN KEY (id_usuario_FK) REFERENCES Usuarios (id_usuario),
    FOREIGN KEY (id_grupo_FK) REFERENCES Grupo (id_grupo)
);
CREATE TABLE Mensajes_privados
(
    id_mensaje_p INT PRIMARY KEY,
    id_usuario   INT,
    mensaje      VARCHAR(300),
    fecha        DATETIME,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios (id_usuario)
);