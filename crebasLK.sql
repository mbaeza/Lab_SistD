/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     26-04-2013 17:08:09                          */
/*==============================================================*/


drop table if exists ADMINISTRADOR;

drop table if exists ASIGNATURA;

drop table if exists COMENTARIO;

drop table if exists CURSO;

drop table if exists CURSO_ASIGNATURA;

drop table if exists ESTUDIANTE;

drop table if exists NOTA;

drop table if exists PROFESOR;

drop table if exists PROFESOR_ASIGNATURA;

drop table if exists USUARIO;

/*==============================================================*/
/* Table: ADMINISTRADOR                                         */
/*==============================================================*/
create table ADMINISTRADOR
(
   RUT_USUARIO          int not null,
   ID_ADMINISTRADOR     int not null,
   CONTRASENA           varchar(8),
   NOMBRE1_ADMINISTRADOR varchar(20),
   NOMBRE2_ADMINISTRADOR varchar(20),
   APELLIDO1_ADMINISTRADOR varchar(20),
   APELLIDO2_ADMINISTRADOR varchar(20),
   TELEFONO             int,
   primary key (RUT_USUARIO, ID_ADMINISTRADOR)
);

/*==============================================================*/
/* Table: ASIGNATURA                                            */
/*==============================================================*/
create table ASIGNATURA
(
   ID_ASIGNATURA        int not null auto_increment,
   ID_NOTA              int not null,
   NOMBRE_ASIGNATURA    varchar(30),
   primary key (ID_ASIGNATURA)
);

/*==============================================================*/
/* Table: COMENTARIO                                            */
/*==============================================================*/
create table COMENTARIO
(
   ID_COMENTARIO        int not null auto_increment,
   RUT_USUARIO          int not null,
   ID_ALUMNO            int not null,
   PRO_RUT_USUARIO      int not null,
   ID_PROFESOR          int not null,
   CUERPO_COMENTARIO    text,
   FECHA_HORA           timestamp,
   primary key (ID_COMENTARIO)
);

/*==============================================================*/
/* Table: CURSO                                                 */
/*==============================================================*/
create table CURSO
(
   ID_CURSO             int not null auto_increment,
   RUT_USUARIO          int not null,
   ID_PROFESOR          int not null,
   CANT_ALUMNO          int,
   primary key (ID_CURSO)
);

/*==============================================================*/
/* Table: CURSO_ASIGNATURA                                      */
/*==============================================================*/
create table CURSO_ASIGNATURA
(
   ID_CURSO             int not null,
   ID_ASIGNATURA        int not null
);

/*==============================================================*/
/* Table: ESTUDIANTE                                            */
/*==============================================================*/
create table ESTUDIANTE
(
   RUT_USUARIO          int not null,
   ID_ALUMNO            int not null,
   ID_CURSO             int not null,
   CONTRASENA           varchar(8),
   NOMBRE1_ESTUDIANTE   varchar(20),
   NOMBRE2_ESTUDIANTE   varchar(20),
   APELLIDO_PATERNO     varchar(20),
   APELLIDO_MATERNO     varchar(20),
   CORREO_ESTUDIANTE    varchar(40),
   primary key (RUT_USUARIO, ID_ALUMNO)
);

alter table ESTUDIANTE comment 'Son los estudiantes inscritos, los que pertenecen a las 20 c';

/*==============================================================*/
/* Table: NOTA                                                  */
/*==============================================================*/
create table NOTA
(
   ID_NOTA              int not null auto_increment,
   PROMEDIO             float,
   primary key (ID_NOTA)
);

/*==============================================================*/
/* Table: PROFESOR                                              */
/*==============================================================*/
create table PROFESOR
(
   RUT_USUARIO          int not null,
   ID_PROFESOR          int not null,
   CONTRASENA           varchar(8),
   NOMBRE1_PROFESOR     varchar(20),
   TELEFONO_PROFESOR    int,
   NOMBRE2_PROFESOR     varchar(20),
   APELLIDO1_PROFESOR   varchar(20),
   primary key (RUT_USUARIO, ID_PROFESOR)
);

alter table PROFESOR comment 'Los profesores son los encargados de impartir el curso, para';

/*==============================================================*/
/* Table: PROFESOR_ASIGNATURA                                   */
/*==============================================================*/
create table PROFESOR_ASIGNATURA
(
   ID_ASIGNATURA        int not null,
   RUT_USUARIO          int not null,
   ID_PROFESOR          int not null
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO
(
   RUT_USUARIO          int not null,
   CONTRASENA           varchar(8),
   primary key (RUT_USUARIO)
);

alter table ADMINISTRADOR add constraint FK_INHERITANCE_3 foreign key (RUT_USUARIO)
      references USUARIO (RUT_USUARIO) on delete restrict on update restrict;

alter table ASIGNATURA add constraint FK_RELATIONSHIP_3 foreign key (ID_NOTA)
      references NOTA (ID_NOTA) on delete restrict on update restrict;

alter table COMENTARIO add constraint FK_RELATIONSHIP_8 foreign key (RUT_USUARIO, ID_ALUMNO)
      references ESTUDIANTE (RUT_USUARIO, ID_ALUMNO) on delete restrict on update restrict;

alter table COMENTARIO add constraint FK_RELATIONSHIP_9 foreign key (PRO_RUT_USUARIO, ID_PROFESOR)
      references PROFESOR (RUT_USUARIO, ID_PROFESOR) on delete restrict on update restrict;

alter table CURSO add constraint FK_RELATIONSHIP_5 foreign key (RUT_USUARIO, ID_PROFESOR)
      references PROFESOR (RUT_USUARIO, ID_PROFESOR) on delete restrict on update restrict;

alter table CURSO_ASIGNATURA add constraint FK_RELATIONSHIP_6 foreign key (ID_CURSO)
      references CURSO (ID_CURSO) on delete restrict on update restrict;

alter table CURSO_ASIGNATURA add constraint FK_RELATIONSHIP_7 foreign key (ID_ASIGNATURA)
      references ASIGNATURA (ID_ASIGNATURA) on delete restrict on update restrict;

alter table ESTUDIANTE add constraint FK_INHERITANCE_2 foreign key (RUT_USUARIO)
      references USUARIO (RUT_USUARIO) on delete restrict on update restrict;

alter table ESTUDIANTE add constraint FK_RELATIONSHIP_4 foreign key (ID_CURSO)
      references CURSO (ID_CURSO) on delete restrict on update restrict;

alter table PROFESOR add constraint FK_INHERITANCE_1 foreign key (RUT_USUARIO)
      references USUARIO (RUT_USUARIO) on delete restrict on update restrict;

alter table PROFESOR_ASIGNATURA add constraint FK_RELATIONSHIP_10 foreign key (RUT_USUARIO, ID_PROFESOR)
      references PROFESOR (RUT_USUARIO, ID_PROFESOR) on delete restrict on update restrict;

alter table PROFESOR_ASIGNATURA add constraint FK_RELATIONSHIP_11 foreign key (ID_ASIGNATURA)
      references ASIGNATURA (ID_ASIGNATURA) on delete restrict on update restrict;

