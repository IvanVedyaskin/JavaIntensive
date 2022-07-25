INSERT INTO "user" (login, password) values
    ('Ivan', 'qwerty123'),
    ('Sanek', 'sanek2022'),
    ('ADMoscow', 'school21'),
    ('Brabus', 'merstakoe'),
    ('Pegas', 'taxithebest');

INSERT INTO chatroom (chatroomName, userid) values
    ('pedago', 1),
    ('MoscowADM', 3),
    ('Dota 2', 2),
    ('CSGO', 4),
    ('Atlantis', 5);

INSERT INTO message (userid, roomid, texts, date) values
    (1, 1, 'random the best', current_date),
    (2, 3, 'zxc na sf', current_date),
    (3, 2, 'vi otchisleni', current_date),
    (4, 5, 'letsgo', current_date),
    (5, 1, 'deadline the end', current_date );

-- drop table if exists chatroom, message, "User";