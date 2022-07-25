CREATE TABLE if not exists "user" (
    id serial primary key,
    login varchar(15) not null,
    password varchar(20) not null
    );

CREATE TABLE if not exists Chatroom (
    id serial primary key,
    chatroomName text,
    userId int not null references "user"
);

CREATE TABLE if not exists Message (
    id serial primary key,
    userId int not null references "user",
    roomId int not null references Chatroom,
    texts text,
    date date
);