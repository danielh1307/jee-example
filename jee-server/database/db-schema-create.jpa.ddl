create sequence hibernate_sequence start 1 increment 1
create table Address (id int4 not null, lifeCylce int4 not null, version int4, hausnummer varchar(255) not null, ort varchar(255) not null, plz varchar(255) not null, strasse varchar(255) not null, primary key (id))
create table Person (id int4 not null, lifeCylce int4 not null, version int4, geburtsdatum varchar(255) not null, nachname varchar(255) not null, vorname varchar(255) not null, ADDRESS_ID int4, primary key (id))
create table Vehicle (VEHICLE_TYPE varchar(31) not null, id int4 not null, lifeCylce int4 not null, version int4, description varchar(255) not null, type int4, modell varchar(255), OWNER_ID int4, primary key (id))
alter table Person add constraint FK7hs06ply1oy9a1735sii1iced foreign key (ADDRESS_ID) references Address
alter table Vehicle add constraint FKird0jsckjobdsnjeof5686hog foreign key (OWNER_ID) references Person
