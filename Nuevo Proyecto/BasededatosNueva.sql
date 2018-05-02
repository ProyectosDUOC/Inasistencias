DROP DATABASE IF EXISTS inasistencia;


CREATE Database inasistencia;

use inasistencia;

CREATE TABLE administrador (
    id_administrador    INT NOT NULL AUTO_INCREMENT,
    rut_administrador   VARCHAR(30) NOT NULL,
    pnombre             VARCHAR(30),
    snombre             VARCHAR(30),
    appaterno           VARCHAR(30),
    apmaterno           VARCHAR(30),
    email               VARCHAR(50) NOT NULL,
    activo              INT,
    PRIMARY KEY(id_administrador)
);

CREATE TABLE alumno (
    id_alumno    INT NOT NULL AUTO_INCREMENT,
    rut_almuno   VARCHAR(30) NOT NULL,
    pnombre      VARCHAR(30),
    snombre      VARCHAR(30),
    appaterno    VARCHAR(30),
    apmaterno    VARCHAR(30),
    email        VARCHAR(50) NOT NULL,
    id_carrera   INT NOT NULL,
    activo       INT,
    PRIMARY KEY(id_alumno)
);

CREATE TABLE carrera (
    id_carrera       INT NOT NULL AUTO_INCREMENT,
    cod_carrera      VARCHAR(30) NOT NULL,
    nombre_carrera   VARCHAR(300) NOT NULL,
    id_director      INT NOT NULL,
    PRIMARY KEY(id_carrera)
);


CREATE TABLE control_usuario (
    id_controlu   INT NOT NULL AUTO_INCREMENT,
    usuario       VARCHAR(30) NOT NULL,
    clave         VARCHAR(30) NOT NULL,
    rut_usuario   VARCHAR(30) NULL,
    id_tipou      INT NOT NULL,
    activo        INT NOT NULL,
    PRIMARY KEY(id_controlu)
);

CREATE TABLE detalle_seccion (
    id_detalle_secc   INT NOT NULL AUTO_INCREMENT,
    id_seccion        INT NOT NULL,
    activo            INT NOT NULL,
    id_alumno         INT NOT NULL,
    PRIMARY KEY(id_detalle_secc)
);

CREATE TABLE director (
    id_director    INT NOT NULL AUTO_INCREMENT,
    rut_director   VARCHAR(30) NOT NULL,
    pnombre        VARCHAR(30),
    snombre        VARCHAR(30),
    appaterno      VARCHAR(30),
    apmaterno      VARCHAR(30),
    email          VARCHAR(50),
    activo         INT,
    PRIMARY KEY(id_director)
);

CREATE TABLE docente (
    id_docente    INT NOT NULL AUTO_INCREMENT,
    rut_docente   VARCHAR(30) NOT NULL,
    pnombre       VARCHAR(30),
    snombre       VARCHAR(30),
    appaterno     VARCHAR(30),
    apmaterno     VARCHAR(30),
    email         VARCHAR(50),
    activo        INT,
    PRIMARY KEY(id_docente)
);

CREATE TABLE estado_correo (
    id_estadoc       INT NOT NULL,
    nombre_estadoc   VARCHAR(30)
);

ALTER TABLE estado_correo ADD CONSTRAINT estado_correo_pk PRIMARY KEY ( id_estadoc );

CREATE TABLE estado_inasistencia (
    id_estadoi       INT NOT NULL,
    nombre_estadoi   VARCHAR(30)
);

ALTER TABLE estado_inasistencia ADD CONSTRAINT estado_inasistencia_pk PRIMARY KEY ( id_estadoi );

CREATE TABLE inasistencia (
    id_inasistencia      INT NOT NULL AUTO_INCREMENT,
    fecha_inasistencia   DATE,
    id_seccion           INT NOT NULL,
    id_alumno            INT NOT NULL,
    id_estadoi           INT NOT NULL,
    id_estadoc           INT NOT NULL,
    PRIMARY KEY(id_inasistencia)
);

CREATE TABLE justificacion (
    id_justificacion      INT NOT NULL AUTO_INCREMENT,
    id_inasistencia       INT NOT NULL,
    fecha_justificacion   DATE NOT NULL,
    id_motivo             INT NOT NULL,
    glosa                 VARCHAR(300),
    PRIMARY KEY(id_justificacion)
);

CREATE TABLE justificacion_imagen (
    id_justificacion_img   INT NOT NULL AUTO_INCREMENT,
    id_justificacion       INT NOT NULL,
    nombre_imagen          VARCHAR(30),
    imagen                 MEDIUMBLOB,
    descripcion            VARCHAR(100),
    PRIMARY KEY(id_justificacion_img)
);

CREATE TABLE motivo (
    id_motivo       INT NOT NULL,
    nombre_motivo   VARCHAR(30)
);

ALTER TABLE motivo ADD CONSTRAINT motivo_pk PRIMARY KEY ( id_motivo );

CREATE TABLE seccion (
    id_seccion    INT NOT NULL AUTO_INCREMENT,
    cod_seccion   VARCHAR(30),
    id_docente    INT NOT NULL,
    semestre      INT,
    anio          INT,
    PRIMARY KEY(id_seccion)
);


CREATE TABLE secretaria (
    id_secretaria    INT NOT NULL AUTO_INCREMENT,
    rut_secretaria   VARCHAR(30),
    pnombre          VARCHAR(30),
    snombre          VARCHAR(30),
    appaterno        VARCHAR(30),
    apmaterno        VARCHAR(30),
    email            VARCHAR(30),
    activo           INT,
    PRIMARY KEY(id_secretaria)
);

CREATE TABLE tipo_usuario (
    id_tipou       INT NOT NULL,
    nombre_tipou   VARCHAR(30)
);

ALTER TABLE tipo_usuario ADD CONSTRAINT tipo_usuario_pk PRIMARY KEY ( id_tipou );

ALTER TABLE alumno
    ADD CONSTRAINT alum_carr_fk FOREIGN KEY ( id_carrera )
        REFERENCES carrera ( id_carrera );

ALTER TABLE carrera
    ADD CONSTRAINT carr_dire_fk FOREIGN KEY ( id_director )
        REFERENCES director ( id_director );

ALTER TABLE control_usuario
    ADD CONSTRAINT cous_tiuso_fk FOREIGN KEY ( id_tipou )
        REFERENCES tipo_usuario ( id_tipou );

ALTER TABLE detalle_seccion
    ADD CONSTRAINT deta_secc_alum_fk FOREIGN KEY ( id_alumno )
        REFERENCES alumno ( id_alumno );

ALTER TABLE detalle_seccion
    ADD CONSTRAINT deta_secc_secc_fk FOREIGN KEY ( id_seccion )
        REFERENCES seccion ( id_seccion );

ALTER TABLE inasistencia
    ADD CONSTRAINT inas_alum_fk FOREIGN KEY ( id_alumno )
        REFERENCES alumno ( id_alumno );

ALTER TABLE inasistencia
    ADD CONSTRAINT inas_esco_fk FOREIGN KEY ( id_estadoc )
        REFERENCES estado_correo ( id_estadoc );

ALTER TABLE inasistencia
    ADD CONSTRAINT inas_esina_fk FOREIGN KEY ( id_estadoi )
        REFERENCES estado_inasistencia ( id_estadoi );

ALTER TABLE inasistencia
    ADD CONSTRAINT inas_secc_fk FOREIGN KEY ( id_seccion )
        REFERENCES seccion ( id_seccion );

ALTER TABLE justificacion_imagen
    ADD CONSTRAINT just_imag_fk FOREIGN KEY ( id_justificacion )
        REFERENCES justificacion ( id_justificacion );

ALTER TABLE justificacion
    ADD CONSTRAINT just_inas_fk FOREIGN KEY ( id_inasistencia )
        REFERENCES inasistencia ( id_inasistencia );

ALTER TABLE justificacion
    ADD CONSTRAINT just_moti_fk FOREIGN KEY ( id_motivo )
        REFERENCES motivo ( id_motivo );

ALTER TABLE seccion
    ADD CONSTRAINT secc_doce_fk FOREIGN KEY ( id_docente )
        REFERENCES docente ( id_docente );

