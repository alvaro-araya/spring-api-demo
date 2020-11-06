/******************************************************************************
 * Alvaro Araya 2020                                                          *
 ******************************************************************************/
use pandemicsdb;

create table tipo_entidad
(
    id     int unsigned auto_increment primary key,
    nombre varchar(250) charset utf8 not null
) character set utf8mb4;

create table patente
(
    id             int unsigned auto_increment primary key,
    guid           varchar(100) charset utf8             not null unique,
    especie        varchar(100) charset utf8             not null,
    descubierto    varchar(100) charset utf8             not null,
    tipo_entidad   int unsigned                          not null,
    fecha_crea     timestamp default current_timestamp() not null,
    fecha_modifica timestamp default current_timestamp() not null on update current_timestamp(),
    constraint patente_tipo_entidad_fk
        foreign key (tipo_entidad) references tipo_entidad (id)
            on update cascade
) character set utf8mb4;

-- BODEGAS @start
INSERT INTO pandemicsdb.tipo_entidad (id, nombre) VALUES (1, 'VIRUS');
INSERT INTO pandemicsdb.tipo_entidad (id, nombre) VALUES (2, 'BACTERIA');
INSERT INTO pandemicsdb.tipo_entidad (id, nombre) VALUES (3, 'HONGOS');
INSERT INTO pandemicsdb.tipo_entidad (id, nombre) VALUES (4, 'OTRO');
-- @end