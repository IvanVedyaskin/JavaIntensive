INSERT INTO "User" (login, password) values
    ('Ivan', 'qwerty123'),
    ('Sanek', 'sanek2022'),
    ('ADMoscow', 'school21');

INSERT INTO chatroom (chatroomName, userid) values
    ('pedago', 1),
    ('MoscowADM', 3),
    ('Dota 2', 2);

INSERT INTO message (userid, roomid, texts, date) values
    (1, 1, 'random the best', current_date),
    (2, 3, 'zxc na sf', current_date),
    (3, 2, 'vi otchisleni', current_date);