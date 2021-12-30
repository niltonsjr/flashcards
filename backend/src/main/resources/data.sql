INSERT INTO tb_rol (nombre) VALUES ('ROLE_USUARIO');
INSERT INTO tb_rol (nombre) VALUES ('ROLE_ADMINISTRADOR');

INSERT INTO tb_usuario (nombre_de_usuario, nombre, apellidos, email, contrasena) VALUES ('juanpg', 'Juan', 'Perez Gonzalez', 'juan@gmail.com', '$2a$10$BTPG8l7y9XdM/BzSbwUbhenrzN537Qja22LaWY9OeoUBxoLAVtb/q');
INSERT INTO tb_usuario (nombre_de_usuario, nombre, apellidos, email, contrasena) VALUES ('mariafs', 'Maria', 'Fernandez Silva', 'maria@gmail.com', '$2a$10$BTPG8l7y9XdM/BzSbwUbhenrzN537Qja22LaWY9OeoUBxoLAVtb/q');

INSERT INTO tb_usuario_rol (usuario_id, rol_id) VALUES (1, 1);
INSERT INTO tb_usuario_rol (usuario_id, rol_id) VALUES (1, 2);
INSERT INTO tb_usuario_rol (usuario_id, rol_id) VALUES (2, 1);

INSERT INTO tb_categoria (nombre, usuario_id) VALUES ('Vocabulario Inglés', 1);
INSERT INTO tb_categoria (nombre, usuario_id) VALUES ('Historia de la Filosofía', 1);
INSERT INTO tb_categoria (nombre, usuario_id) VALUES ('Ecología de los Seres Vivos', 1);

INSERT INTO tb_categoria (nombre, usuario_id) VALUES ('Verbos de Francés', 2);
INSERT INTO tb_categoria (nombre, usuario_id) VALUES ('Matemáticas', 2);
INSERT INTO tb_categoria (nombre, usuario_id) VALUES ('Clasificación de la Química', 2);

INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Fat', 'Gordo', TRUE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 2, 0, 1, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Thin', 'Delgado', FALSE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 0, 1, 1, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Tall', 'Alto', TRUE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 3, 1, 1, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Short', 'Bajo/corto', TRUE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 1, 0, 1, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Old', 'Viejo', TRUE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 1, 0, 1, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Young', 'Joven', TRUE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 1, 0, 1, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Funny', 'Divertido', FALSE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 0, 2, 1, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Chubby', 'Regordete', TRUE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 1, 0, 1, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Slim', 'Delgado', FALSE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 0, 1, 1, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Blond', 'Rubio/a', FALSE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 0, 3, 1, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Expensive', 'Caro', FALSE, null, 0, 0, 1, 1);

INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('CONFUCIO', 'Saber que se sabe lo que se sabe y que no se sabe lo que no se sabe; he aquí el verdadero saber.', TRUE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 1, 0, 2, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('ARISTÓTELES', 'El ignorante afirma, el sabio duda y reflexiona.', FALSE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 0, 3, 2, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('RENÉ DESCARTES', 'Daría todo lo que sé, por la mitad de lo que ignoro.', TRUE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 3, 0, 2, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('SÓCRATES', 'Sólo sé que no sé nada.', TRUE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 1, 0, 2, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('FRIEDRICH NIETZSCHE', 'Los monos son demasiado buenos para que el hombre pueda descender de ellos.', TRUE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 3, 0, 2, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('MAQUIAVELO', 'Los hombres ofenden antes al que aman, que al que temen', TRUE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 2, 0, 2, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('PLATÓN', 'Los sabios hablan porque tienen algo que decir, los tontos hablan porque tienen que decir algo.', FALSE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 0, 4, 2, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('ERASMO DE ROTTERDAM', 'En el estudio no existe la saciedad.', FALSE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 0, 2, 2, 1);

INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Ecosistema', 'Sistema biológico constituido por una comunidad de seres vivos y el medio natural en que viven.', TRUE, TIMESTAMP WITH TIME ZONE '2021-08-10T20:50:07Z', 1, 0, 2, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Bioma', 'Cada unidad ecológica en que se divide la biosfera atendiendo a un conjunto de factores climáticos y geológicos que determinan el tipo de vegetación y fauna.', FALSE, TIMESTAMP WITH TIME ZONE '2021-08-10T20:50:07Z', 1, 2, 3, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Hábitat', 'Conjunto de factores físicos y geográficos que inciden en el desarrollo de un individuo, una población, una especie o grupo de especies determinados.', TRUE, TIMESTAMP WITH TIME ZONE '2021-08-10T20:50:07Z', 1, 0, 3, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Nicho Ecológico', 'es un término que describe la posición relacional de una especie o población en un ecosistema. En otras palabras, cuando hablamos de nicho ecológico, nos referimos a la «ocupación» o a la función que desempeña cierto individuo dentro de una comunidad.', TRUE, TIMESTAMP WITH TIME ZONE '2021-08-10T20:50:07Z', 3, 0, 3, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Biotopo', 'El biotopo es casi sinónimo del término hábitat con la diferencia de que hábitat se refiere a las especies o poblaciones mientras que biotopo se refiere a las comunidades biológicas.', TRUE, TIMESTAMP WITH TIME ZONE '2021-08-10T20:50:07Z', 1, 0, 3, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Área de reserva', 'es un área protegida, de importancia para la vida silvestre, flora o fauna, o con rasgos geológicos de especial interés que es protegida y manejada por el hombre, con fines de conservación y de proveer oportunidades de investigación y de educación.', TRUE, TIMESTAMP WITH TIME ZONE '2021-08-10T20:50:07Z', 2, 0, 3, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Factores bióticos', 'Los factores bióticos son todos los organismos de un ecosistema que sobreviven, es decir, los que tienen vida.', FALSE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 0, 4, 3, 1);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Depredador', 'Que caza animales de otra especie para alimentarse.', FALSE, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07Z', 0, 2, 3, 1);

INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Aller', 'Ir', TRUE, TIMESTAMP WITH TIME ZONE '2021-05-05T10:30:05Z', 2, 1, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Finir', 'Acabar', TRUE, TIMESTAMP WITH TIME ZONE '2021-05-05T10:30:05Z', 1, 0, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Choisir', 'Elegir', FALSE, TIMESTAMP WITH TIME ZONE '2021-05-05T10:30:05Z', 0, 1, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Grandir', 'Crecer', TRUE, TIMESTAMP WITH TIME ZONE '2021-05-05T10:30:05Z', 1, 0, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Obéir', 'Obéir', TRUE, TIMESTAMP WITH TIME ZONE '2021-05-05T10:30:05Z', 1, 0, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Réfléchir', 'Reflexionar', TRUE, TIMESTAMP WITH TIME ZONE '2021-05-05T10:30:05Z', 1, 0, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Réussir', 'Aprobar', TRUE, TIMESTAMP WITH TIME ZONE '2021-05-05T10:30:05Z', 1, 0, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Rougir', 'Sonrojarse', TRUE, TIMESTAMP WITH TIME ZONE '2021-05-05T10:30:05Z', 1, 0, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Vieillir', 'Envejecer', TRUE, TIMESTAMP WITH TIME ZONE '2021-05-05T10:30:05Z', 1, 0, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Offrir', 'Regalar', TRUE, TIMESTAMP WITH TIME ZONE '2021-05-05T10:30:05Z', 1, 0, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Couvrir', 'Cubrir', TRUE, TIMESTAMP WITH TIME ZONE '2021-05-05T10:30:05Z', 1, 0, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Cueillir', 'Coger (recolectar)', TRUE, TIMESTAMP WITH TIME ZONE '2021-05-05T10:30:05Z', 1, 0, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Découvir', 'descubrir', TRUE, TIMESTAMP WITH TIME ZONE '2021-05-05T10:30:05Z', 1, 0, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Ouvrir', 'Abrir', FALSE, null, 0, 0, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Souffrir', 'Sufrir', FALSE, null, 0, 0, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Partir', 'Irse', FALSE, null, 0, 0, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Courir', 'correr', FALSE, null, 0, 0, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Venir', 'Venir', FALSE, null, 0, 0, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Dire', 'Decir', FALSE, null, 0, 0, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Rire', 'Rire', FALSE, null, 0, 0, 4, 2);

INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('¿Qué es una ecuación?', 'Una igualdad', TRUE, TIMESTAMP WITH TIME ZONE '2021-05-05T10:30:05Z', 2, 1, 5, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Definición Ecuación Diferencial', 'Cualquier ecuación que relacione una función desconocida con su variable o variables independientes y alguna de sus derivadas. Cuando la función incógnita depende de una sola variable independiente entonces las derivadas que aparecerán en la ecuación serán ordinarias y serán ecuaciones diferenciales ordinarias.', TRUE, TIMESTAMP WITH TIME ZONE '2021-05-05T10:30:05Z', 1, 0, 5, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Tipos de Ecuaciones diferenciales', '1. EDO: cuaciones diferenciales ordinarias. 2. EDP: Ecuaciones diferenciales en derivadas parciales', FALSE, TIMESTAMP WITH TIME ZONE '2021-05-05T10:30:05Z', 0, 1, 5, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Solución singular', 'Se llama solución singular a cualquier solución particular de la ecuación que no se pueda deducir a partir de su solución general.', TRUE, TIMESTAMP WITH TIME ZONE '2021-05-05T10:30:05Z', 1, 0, 4, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Interpretar una función n-paramétrica de curvas como una solución general de una ecuación de orden n', 'Interpretar una función n-paramétrica de curvas como una solución general de una ecuación de orden n', TRUE, TIMESTAMP WITH TIME ZONE '2021-05-05T10:30:05Z', 1, 0, 5, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Problemas de valor inicial o de Cauchy: Tipos', 'Nos encontramos con un problema de valor inicial o de Cauchy si las condiciones iniciales vienen dadas en vez de un punto "Xo", si vienen en diferentes puntos entonces en vez de decir que tenemos un problema de valor inicial, se dice que se tiene un problema de contorno.', TRUE, TIMESTAMP WITH TIME ZONE '2021-05-05T10:30:05Z', 1, 0, 5, 2);

INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Química General', 'Es la ciencia que estudia tanto la composición, estructura y propiedades de la materia como los cambios que ésta experimenta durante las reacciones químicas y su relación con la energía.', TRUE, TIMESTAMP WITH TIME ZONE '2021-06-05T10:30:05Z', 2, 1, 6, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Química Especial', 'Es aquella que estudia la Química tanto orgánica como inorgánica y la química analítica.', TRUE, TIMESTAMP WITH TIME ZONE '2021-06-05T10:30:05Z', 1, 0, 6, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Análisis Cualitativo: este análisis identifica que clase de elementos forman un compuesto.', 'Análisis Cuantitativo: este análisis identifica que clase de elementos forman un compuesto así por ejemplo si tenemos la molécula agua decimos que está formada por dos átomos de hidrógeno y uno de oxígeno', FALSE, TIMESTAMP WITH TIME ZONE '2021-06-05T10:30:05Z', 0, 1, 6, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Química Aplicada', 'Dentro de esta clasificación anotamos las siguientes: Geología Mineralogía Petroquimica', TRUE, TIMESTAMP WITH TIME ZONE '2021-06-05T10:30:05Z', 1, 0, 6, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Bioquímica', 'Estudia las diferentes reacciones químicas que se realizan en el interior de los seres vivos.', TRUE, TIMESTAMP WITH TIME ZONE '2021-06-05T10:30:05Z', 1, 0, 6, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Mineralogía', 'Estudia todos los minerales que se pueden extraer en la corteza terrestre por ejemplo . el oro, plata , estaño , etc.', TRUE, TIMESTAMP WITH TIME ZONE '2021-06-05T10:30:05Z', 1, 0, 6, 2);
INSERT INTO tb_tarjeta (frontal, trasera, conocida, fecha_ultima_respuesta, total_conocidas, total_no_conocidas, categoria_id, usuario_id) VALUES ('Petroquímica', 'Estudia el petróleo y sus derivados: cosméticos, acetona, diésel, et.', FALSE, null, 0, 0, 6, 2);