
create table person
(
   id integer not null,
   parentId integer,
   name varchar(200) not null,
   color varchar(100) not null,
   primary key(id)
);

insert into person values(1, 0, "Jai", "red");
insert into person values(2, 0, "Arya", "blue");
insert into person values(3, 1, "Dev", "yellow"); 
