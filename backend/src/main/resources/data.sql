INSERT INTO tb_rol (nombre) VALUES ('usuario');
INSERT INTO tb_rol (nombre) VALUES ('administrador');

INSERT INTO tb_usuario (nombre_de_usuario, nombre, apellidos, email, contrasena) VALUES ('juanpg', 'Juan', 'Perez Gonzalez', 'juan@gmail.com', '123456');
INSERT INTO tb_usuario (nombre_de_usuario, nombre, apellidos, email, contrasena) VALUES ('mariafs', 'Maria', 'Fernandez Silva', 'maria@gmail.com', '123456');

INSERT INTO tb_usuario_rol (usuario_id, rol_id) VALUES (1, 1);
INSERT INTO tb_usuario_rol (usuario_id, rol_id) VALUES (1, 2);
INSERT INTO tb_usuario_rol (usuario_id, rol_id) VALUES (2, 1);

INSERT INTO tb_categoria (nombre, usuario_id) VALUES ('Inglés', 1);
INSERT INTO tb_categoria (nombre, usuario_id) VALUES ('Geografía', 1);
INSERT INTO tb_categoria (nombre, usuario_id) VALUES ('Historia', 1);
INSERT INTO tb_categoria (nombre, usuario_id) VALUES ('Inglés', 2);
INSERT INTO tb_categoria (nombre, usuario_id) VALUES ('Francés', 2);
INSERT INTO tb_categoria (nombre, usuario_id) VALUES ('Matemáticas', 2);

INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('frontal Juan 1', 'trasera Juan 1', TRUE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z', 5, 0, 1, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('frontal Juan 2', 'trasera Juan 2', FALSE, TIMESTAMP WITH TIME ZONE '2021-10-13T20:50:07.12345Z', 0, 1, 2, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('frontal Juan 3', 'trasera Juan 3', TRUE, TIMESTAMP WITH TIME ZONE '2020-05-15T20:50:07.12345Z', 3, 1, 3, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('frontal Juan 4', 'trasera Juan 4', TRUE, TIMESTAMP WITH TIME ZONE '2020-05-15T20:50:07.12345Z', 1, 0, 1, 1);

INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('frontal Maria 1', 'trasera Maria 1', TRUE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z', 2, 1, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('frontal Maria 2', 'trasera Maria 2', TRUE, TIMESTAMP WITH TIME ZONE '2021-10-13T20:50:07.12345Z', 1, 0, 5, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('frontal Maria 3', 'trasera Maria 3', FALSE, TIMESTAMP WITH TIME ZONE '2020-05-15T20:50:07.12345Z', 0, 1, 6, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('frontal Maria 4', 'trasera Maria 4', TRUE, TIMESTAMP WITH TIME ZONE '2020-05-15T20:50:07.12345Z', 1, 0, 4, 2);