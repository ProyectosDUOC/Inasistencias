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
    rut_alumno   VARCHAR(30) NOT NULL,
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
    nombre_imagen          VARCHAR(50),
    descripcion            VARCHAR(100),
    PRIMARY KEY(id_justificacion_img)
);

CREATE TABLE motivo (
    id_motivo       INT NOT NULL,
    nombre_motivo   VARCHAR(50)
);

ALTER TABLE motivo ADD CONSTRAINT motivo_pk PRIMARY KEY ( id_motivo );

CREATE TABLE seccion (
    id_seccion    INT NOT NULL AUTO_INCREMENT,
    cod_seccion   VARCHAR(30),
    cod_ramo      VARCHAR(30),
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


CREATE TABLE global_semestre (
    id_global      INT NOT NULL,
    semestre       INT NOT NULL,
    anio           INT NOT NULL,
    fecha_inicio   DATE,
    fecha_termino  DATE,
    PRIMARY KEY(id_global)
);

ALTER TABLE tipo_usuario ADD CONSTRAINT tipo_usuario_pk PRIMARY KEY ( id_tipou );

CREATE TABLE ramo (
    cod_ramo      VARCHAR(30) NOT NULL,
    nombre_ramo   VARCHAR(100) NOT NULL,
    PRIMARY KEY(cod_ramo)
);

CREATE TABLE reporte_secretaria (
    id_reporte         INT NOT NULL AUTO_INCREMENT,
    id_inasistencia    INT NOT NULL,
    id_justificacion   INT NOT NULL,
    id_secretaria      INT NOT NULL,
    id_director        INT NOT NULL,
    id_alumno          INT NOT NULL,
    semestre           INT NOT NULL,
    anio               INT NOT NULL,
    activo             INT NOT NULL,
    PRIMARY KEY(id_reporte)
);

ALTER TABLE reporte_secretaria
    ADD CONSTRAINT repo_inas_fk FOREIGN KEY ( id_inasistencia )
        REFERENCES inasistencia ( id_inasistencia );
ALTER TABLE reporte_secretaria
    ADD CONSTRAINT repo_justi_fk FOREIGN KEY ( id_justificacion )
        REFERENCES justificacion ( id_justificacion );
ALTER TABLE reporte_secretaria
    ADD CONSTRAINT repo_secre_fk FOREIGN KEY ( id_secretaria )
        REFERENCES secretaria ( id_secretaria );
ALTER TABLE reporte_secretaria
    ADD CONSTRAINT repo_dire_fk FOREIGN KEY ( id_director )
        REFERENCES director ( id_director );
ALTER TABLE reporte_secretaria
    ADD CONSTRAINT repo_alu_fk FOREIGN KEY ( id_alumno )
        REFERENCES alumno ( id_alumno );

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

ALTER TABLE seccion
    ADD CONSTRAINT secc_ramo_fk FOREIGN KEY ( cod_ramo )
        REFERENCES ramo ( cod_ramo );



-- director
INSERT INTO director(rut_director,pnombre,snombre,appaterno,apmaterno,email,activo) VALUES('3000','Carlos','Alberto','Orellana','Aguayo','controlinasistencia@gmail.com',1);
-- Carreras -- necesitan tener un director
INSERT INTO carrera(cod_carrera,nombre_carrera,id_director) VALUES('14460-03','ANALISTA PROGRAMADOR COMPUTACIONAL',1);
-- docente
INSERT INTO docente(rut_docente,pnombre,snombre,appaterno,apmaterno,email,activo) VALUES('2001','Gabriel','Alonso ','Parra ','Ovalle ','gabparraprofesor@gmail.com',1);
INSERT INTO docente(rut_docente,pnombre,snombre,appaterno,apmaterno,email,activo) VALUES('2002','Cristian','Orlando','Garcia','Guitierrez','cris.garciaguprofesor@gmail.com',1);
INSERT INTO docente(rut_docente,pnombre,snombre,appaterno,apmaterno,email,activo) VALUES('2003','Juan','Esteban','Harrys','Moure','j.harrysprofesor@gmail.com',1);
-- coordinador ADMINISTRADOR
INSERT INTO administrador(rut_administrador,pnombre,snombre,appaterno,apmaterno,email,activo) VALUES('4001','Sebastian','','Orrego','Aguayo','controlinasistencia@gmail.com',1);
INSERT INTO administrador(rut_administrador,pnombre,snombre,appaterno,apmaterno,email,activo) VALUES('4002','Benjamin','Elias','Mora','Torres','controlinasistencia@gmail.com',1);
-- secretaria
INSERT INTO secretaria(rut_secretaria,pnombre,snombre,appaterno,apmaterno,email,activo) VALUES('1001','Carla','Anai','Moya','Torres','controlinasistencia@gmail.com',1);
-- alumnos de 10 - 28 (Acorde al excel)
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('10','Benjamin','Elias','Mora','Torres','b.morat@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('11','Sebastian','Isaac','Orrego','Aguayo','b.morat@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('12','Carlos','Esteban','huentz','la Paz','b.morat@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('13','Joseph','El','a','Torres','b.morat@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('14','Cristian','','ra','rres','b.morat@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('15','Harrys','','ora','orres','b.morat@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('16','Benjamin','Elias','Mora','Torres','b.morat@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('17','Benjamin','Elias','Mora','Torres','b.morat@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('18','Benjamin','Elias','Mora','Torres','b.morat@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('19','Benjamin','Elias','Mora','Torres','b.morat@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('20','Benjamin','Elias','Mora','Torres','b.morat@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('21','Benjamin','Elias','Mora','Torres','b.morat@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('22','Jack','Johnny','Sparow','Deep','car.orellanaa@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('23','Steve','John','Rogers','Storm','s.orregoa@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('24','Carlos','Alberto','Orellana','Aguayo','car.orellanaa@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('25','Sebastian','Isaac','Orrego','Aguayo','s.orregoa@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('26','Benjamin','Elias','Mora','Torres','b.morat@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('27','Benjamin','Elias','Mora','Torres','b.morat@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('28','Benjamin','Elias','Mora','Torres','b.morat@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('29','Benjamin','Elias','Mora','Torres','b.morat@alumnos.duoc.cl',1,1);
INSERT INTO alumno(rut_alumno,pnombre,snombre,appaterno,apmaterno,email,id_carrera,activo) VALUES('30','Benjamin','Elias','Mora','Torres','b.morat@alumnos.duoc.cl',1,1);

-- uSUARIO Y CLAVE DE LOS USUARIOS 
-- control de usuario login (RF3)
INSERT INTO tipo_usuario VALUES(1,'alumno');
INSERT INTO tipo_usuario VALUES(2,'docente');
INSERT INTO tipo_usuario VALUES(3,'director');
INSERT INTO tipo_usuario VALUES(4,'administrador');
INSERT INTO tipo_usuario VALUES(5,'secretaria');
-- ACCESO secretaria
INSERT INTO control_usuario (usuario,clave,rut_usuario,id_tipou,activo) VALUES('1001','1234','1001',5,1);
-- ACCESO coordinador
INSERT INTO control_usuario (usuario,clave,rut_usuario,id_tipou,activo) VALUES('4002','1234','4002',4,1);
-- ACCESO Docente
INSERT INTO control_usuario (usuario,clave,rut_usuario,id_tipou,activo) VALUES('2001','1234','2001',2,1);
INSERT INTO control_usuario (usuario,clave,rut_usuario,id_tipou,activo) VALUES('2002','1234','2002',2,1);
INSERT INTO control_usuario (usuario,clave,rut_usuario,id_tipou,activo) VALUES('2003','1234','2003',2,1);
-- ACCESO director
INSERT INTO control_usuario (usuario,clave,rut_usuario,id_tipou,activo) VALUES('3000','1234','3000',3,1);
-- ACCESO alumno
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('10','1234','10',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('11','1234','11',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('12','1234','12',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('13','1234','13',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('14','1234','14',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('15','1234','15',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('16','1234','16',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('17','1234','17',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('18','1234','18',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('19','1234','19',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('20','1234','20',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('21','1234','21',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('22','1234','22',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('23','1234','23',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('24','1234','24',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('25','1234','25',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('26','1234','26',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('27','1234','27',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('28','1234','28',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('29','1234','29',1,1);
INSERT INTO control_usuario(usuario,clave,rut_usuario,id_tipou,activo) VALUES('30','1234','30',1,1);
-- Ramos asignaturas que imparten
INSERT INTO ramo VALUES('ASO4461','ADMINISTRACION DE SISTEMAS OPERATIVOS');
INSERT INTO ramo VALUES('DEJ4501','DESARROLLO EN JAVA');
INSERT INTO ramo VALUES('DEN4501','DESARROLLO EN .NET');
INSERT INTO ramo VALUES('INU411','INGLES INTERMEDIO II');
INSERT INTO ramo VALUES('PEI110','EMPRENDIMIENTO E INNOVACION');
INSERT INTO ramo VALUES('PFC040','DOCTRINA SOCIAL DE LA IGLESIA');

INSERT INTO seccion(cod_seccion,cod_ramo,id_docente,semestre,anio) VALUES('ASO4461-002D','ASO4461',2,1,2018);
INSERT INTO seccion(cod_seccion,cod_ramo,id_docente,semestre,anio) VALUES('DEJ4501-001','DEJ4501',2,1,2018);
INSERT INTO seccion(cod_seccion,cod_ramo,id_docente,semestre,anio) VALUES('DEJ4501-002','DEJ4501',2,1,2018);
INSERT INTO seccion(cod_seccion,cod_ramo,id_docente,semestre,anio) VALUES('DEJ4501-003','DEJ4501',2,1,2018);
INSERT INTO seccion(cod_seccion,cod_ramo,id_docente,semestre,anio) VALUES('PEI110-030D','PEI110',1,1,2018);
INSERT INTO seccion(cod_seccion,cod_ramo,id_docente,semestre,anio) VALUES('ASO4461-001D','ASO4461',3,1,2018);


INSERT INTO detalle_seccion(id_seccion,activo,id_alumno) VALUES(2,1,1);
INSERT INTO detalle_seccion(id_seccion,activo,id_alumno) VALUES(2,1,2);
INSERT INTO detalle_seccion(id_seccion,activo,id_alumno) VALUES(2,1,3);


INSERT INTO motivo VALUES (0,'');
INSERT INTO motivo VALUES (1,'Economicos');
INSERT INTO motivo VALUES (2,'Salud');
INSERT INTO motivo VALUES (3,'Laboral');
INSERT INTO motivo VALUES (4,'Tramite');
INSERT INTO motivo VALUES (5,'No entiendo la materia');
INSERT INTO motivo VALUES (6,'No le entiendo al profesor');
INSERT INTO motivo VALUES (7,'Me quede dormido');
INSERT INTO motivo VALUES (8,'Problemas familiares varios');
INSERT INTO motivo VALUES (9,'Problemas con el transporte');
INSERT INTO motivo VALUES (10,'Actividad extra-programatica del instituto');
INSERT INTO motivo VALUES (11,'Otros');

INSERT INTO estado_correo VALUES(0,'ENVIADO 0 VEZ');
INSERT INTO estado_correo VALUES(1,'ENVIADO 1 VEZ');
INSERT INTO estado_correo VALUES(2,'ENVIADO 2 VEZ');
INSERT INTO estado_correo VALUES(3,'ENVIADO 3 VEZ');
INSERT INTO estado_correo VALUES(7,'Busqueda de ID');

INSERT INTO estado_inasistencia VALUES(0,'Subido');
INSERT INTO estado_inasistencia VALUES(1,'Enviado al correo');
INSERT INTO estado_inasistencia VALUES(2,'Justificado por Alumno');
INSERT INTO estado_inasistencia VALUES(3,'Recibido por Secretaria');
INSERT INTO estado_inasistencia VALUES(4,'Aprobado por Docente');
INSERT INTO estado_inasistencia VALUES(5,'No Aprobado por Docente');
INSERT INTO estado_inasistencia VALUES(6,'Aprobado por Director');
INSERT INTO estado_inasistencia VALUES(7,'No Aprobado por Director');
INSERT INTO estado_inasistencia VALUES(8,'Apelado por alumno');


INSERT INTO inasistencia (fecha_inasistencia,id_seccion,id_alumno,id_estadoi,id_estadoc) VALUES ('2018-03-12',2,1,1,1);
INSERT INTO inasistencia (fecha_inasistencia,id_seccion,id_alumno,id_estadoi,id_estadoc) VALUES ('2018-03-10',2,2,1,1);
INSERT INTO inasistencia (fecha_inasistencia,id_seccion,id_alumno,id_estadoi,id_estadoc) VALUES ('2018-03-09',2,3,1,1);
INSERT INTO inasistencia (fecha_inasistencia,id_seccion,id_alumno,id_estadoi,id_estadoc) VALUES ('2018-03-12',2,1,1,1);
INSERT INTO inasistencia (fecha_inasistencia,id_seccion,id_alumno,id_estadoi,id_estadoc) VALUES ('2018-04-10',2,2,2,1);
INSERT INTO justificacion(id_inasistencia,fecha_justificacion,id_motivo,glosa) VALUES (5,'2018-05-02',5,'El profesor explico muy rapido y me puse al dia');

INSERT INTO global_semestre VALUES(1,1,2018,'2018-03-05','2018-06-30');

-- UPDATE global_semestre SET semestre=1 where id_global=1;
