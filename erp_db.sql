drop database dbtes;
drop user postgres;
create user postgres with password '310502';
create database dbtes with template=template0 owner=postgres;
\connect dbtes;


create table Users(
    user_id  Serial primary key,
    username varchar(20) not null,
    email varchar(50) not null,
    password text  not null,
    departement_id Integer not null,
    Role_id Integer  not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)


create table Departement(
    departement_id  Serial primary key,
    departement_name varchar(50) not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)


create table Role(
    role_id  Serial primary key,
    role_name varchar(20) not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)


create table Material_Request(
    material_request_id  Serial primary key,
    material_number integer DEFAULT nextval('nomor_urut_seq'::regclass),
    created_by  Integer not null,
    approved_by  Integer null,
    approved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    delete_by  Integer  null,
    status varchar(20) not null,
    description_rejected text not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)

CREATE SEQUENCE material_number_seq START 1 INCREMENT 1;

create  table Material_Request_Item(
    material_request_item_id  serial primary key ,
    material_request_id Integer not null,
    item_id Integer not null,
    quantity Integer not null,
    description text not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
)



create table Item(
    item_id serial primary key,
    item_name varchar(50) not null,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)


ALTER TABLE Material_Request
ADD CONSTRAINT fk_created_by
foreign key (created_by) references users(user_id);

ALTER TABLE Material_Request
ADD CONSTRAINT fk_approved_by
foreign key (approved_by) references users(user_id);

ALTER TABLE Material_Request
ADD CONSTRAINT fk_delete_by
foreign key (delete_by) references users(user_id);

ALTER TABLE users
ADD CONSTRAINT fk_role_id
foreign key (role_id) references role(role_id);

ALTER TABLE users
ADD CONSTRAINT fk_departement_id
foreign key (departement_id) references departement(departement_id);


ALTER TABLE Material_Request_Item
ADD CONSTRAINT fk_item_id
foreign key (item_id) references item(item_id);

ALTER TABLE Material_Request_Item
ADD CONSTRAINT fk_material_request_id
foreign key (material_request_id) references Material_Request(material_request_id);

INSERT INTO departement(departement_name) VALUES('Production')
INSERT INTO departement(departement_name) VALUES('Warehouse')

INSERT INTO role(role_name) VALUES('Admin')
INSERT INTO role(role_name) VALUES('Member')

INSERT INTO item(item_name) VALUES('Cat pelindung')
INSERT INTO item(item_name) VALUES('Pipa')
INSERT INTO item(item_name) VALUES('Pompa')
INSERT INTO item(item_name) VALUES('fitting')
INSERT INTO item(item_name) VALUES('oli')