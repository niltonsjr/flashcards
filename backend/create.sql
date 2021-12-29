
    create table tb_categoria (
       id int8 generated by default as identity,
        nombre varchar(255),
        usuario_id int8,
        primary key (id)
    );

    create table tb_rol (
       id int8 generated by default as identity,
        nombre varchar(255),
        primary key (id)
    );

    create table tb_tarjeta (
       id int8 generated by default as identity,
        conocida boolean not null,
        fecha_ultima_respuesta TIMESTAMP WITHOUT TIME ZONE,
        frontal TEXT,
        total_conocidas int4,
        total_no_conocidas int4,
        trasera TEXT,
        categoria_id int8,
        usuario_id int8,
        primary key (id)
    );

    create table tb_usuario (
       id int8 generated by default as identity,
        apellidos varchar(255),
        contrasena varchar(255),
        email varchar(255),
        nombre varchar(255),
        nombre_de_usuario varchar(255),
        primary key (id)
    );

    create table tb_usuario_rol (
       usuario_id int8 not null,
        rol_id int8 not null,
        primary key (usuario_id, rol_id)
    );

    alter table if exists tb_usuario 
       add constraint UK_spmnyb4dsul95fjmr5kmdmvub unique (email);

    alter table if exists tb_usuario 
       add constraint UK_kom8ryxusolwy2q776323kpx0 unique (nombre_de_usuario);

    alter table if exists tb_categoria 
       add constraint FKivayrej1eklq4vli06pyyowvu 
       foreign key (usuario_id) 
       references tb_usuario;

    alter table if exists tb_tarjeta 
       add constraint FKi2mgsxt9l3tprgjl25rjdgtqr 
       foreign key (categoria_id) 
       references tb_categoria;

    alter table if exists tb_tarjeta 
       add constraint FKepkbeq50ik1x559kwc1usrskl 
       foreign key (usuario_id) 
       references tb_usuario;

    alter table if exists tb_usuario_rol 
       add constraint FK1y8qrtbo8dl1ilw2yhogn7tm7 
       foreign key (rol_id) 
       references tb_rol;

    alter table if exists tb_usuario_rol 
       add constraint FK57sgp4f2f6jrmb6idohmhka6 
       foreign key (usuario_id) 
       references tb_usuario;
