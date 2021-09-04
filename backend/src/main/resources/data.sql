INSERT INTO `USERS`(`USERNAME`)
VALUES
    ('dnt00'),
    ('dnt01'),
    ('dnt02'),
    ('dnt03'),
    ('dnt04');

INSERT INTO `CHAT_ROOM`(`SENDER`, `RECIPIENT`, `CHAT_ID`)
VALUES
    ('dnt00', 'dnt01', 'dnt00-dnt01'),
    ('dnt01', 'dnt00', 'dnt00-dnt01');

INSERT INTO `FRIEND_SHIP`(`ME`, `YOU`)
VALUES
    ('dnt00', 'dnt01'),
    ('dnt01', 'dnt00'),
    ('dnt00', 'dnt02'),
    ('dnt02', 'dnt00'),
    ('dnt00', 'dnt03'),
    ('dnt03', 'dnt00'),
    ('dnt01', 'dnt02'),
    ('dnt02', 'dnt01');

INSERT INTO `MESSAGE`(`CONTENT`, `SENDER`, `RECIPIENT`, `CHAT_ID`, `TIME`)
VALUES
    ('hello :))', 'dnt00', 'dnt01', 'dnt00-dnt01', NOW());
