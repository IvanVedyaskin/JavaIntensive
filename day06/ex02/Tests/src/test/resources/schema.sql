drop table if exists shop;
create table if not exists shop (
    id integer generated by default as identity primary key,
    name varchar(80),
    coast integer
);
