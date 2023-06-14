INSERT INTO MEMBER_tb (id, name)
VALUES ('pre-1', '김채원')
     , ('pre-2', '카즈하')
     , ('pre-3', '은채만채')
     , ('pre-4', '팜하니');

INSERT INTO ROOM_TB (room_id, reg_date, type)
VALUES ('pre-0', NOW(), 0)
     , ('pre-1', NOW(), 1);

INSERT INTO USER_ROOM_TB (user_seq, room_seq, reg_date)
VALUES (1, 1, NOW())
     , (2, 1, NOW())
     , (1, 2, NOW())
     , (2, 2, NOW())
     , (3, 2, NOW())
     , (4, 2, NOW());
