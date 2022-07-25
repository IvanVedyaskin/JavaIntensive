CREATE TABLE if not exists "User" (
    id serial primary key,
    login varchar(15) not null,
    password varchar(20) not null
);

CREATE TABLE if not exists Chatroom (
    id serial primary key,
    chatroomName text,
    userId int not null references "User"
);

CREATE TABLE if not exists Message (
    id serial primary key,
    userId int not null references "User",
    roomId int not null references Chatroom,
    texts text,
    date date
);