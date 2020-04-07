CREATE DATABASE musala CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE musala.nt_gateway(
id  BIGINT NOT NULL AUTO_INCREMENT,
serial_number varchar(20) not null,
description_name varchar(50),
ipv4 varchar(15),
PRIMARY KEY (id),
UNIQUE KEY(serial_number)
 );

CREATE TABLE musala.nt_device(
uid BIGINT NOT NULL AUTO_INCREMENT,
vendor varchar(20),
creation_date date ,
state boolean,
gateway_id BIGINT ,
primary key(uid),
FOREIGN KEY (gateway_id)
        REFERENCES  musala.nt_gateway(id)
);

