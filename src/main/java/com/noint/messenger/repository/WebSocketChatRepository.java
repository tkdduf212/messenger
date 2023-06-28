package com.noint.messenger.repository;

import com.noint.messenger.dto.RoomListDTO;
import com.noint.messenger.entity.QMember;
import com.noint.messenger.entity.QRoom;
import com.noint.messenger.entity.QUserRoom;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WebSocketChatRepository {
    private final JPAQueryFactory queryFactory;

    public List<RoomListDTO.ParticipatingRoomInfo> roomListInfo(Long userSeq) {
        QUserRoom userRoom1 = new QUserRoom(QUserRoom.userRoom);
        QUserRoom userRoom2 = new QUserRoom(QUserRoom.userRoom+"2");
        QRoom room = new QRoom(QRoom.room);
        QMember member = new QMember(QMember.member);
        List<RoomListDTO.ParticipatingRoomInfo> infoList = queryFactory.select(
                        Projections.constructor(
                                RoomListDTO.ParticipatingRoomInfo.class,
                                userRoom2.roomSeq.as("roomSeq"),
                                room.roomId.as("roomId"),
//                                Expressions.stringTemplate("group_concat", member.name).as("names"),
                                userRoom2.regDate.as("regDate")
                        )
                )
                .from(userRoom1)
                .innerJoin(userRoom2)
                .on(userRoom1.roomSeq.eq(userRoom2.roomSeq).and(userRoom1.userSeq.ne(userRoom2.userSeq)))
                .innerJoin(room)
                .on(userRoom2.roomSeq.eq(room.seq))
                .innerJoin(member)
                .on(member.seq.eq(userRoom2.userSeq))
                .where(userRoom1.userSeq.eq(userSeq).and(userRoom1.delDate.isNull()))
                .fetch();

        return infoList;
    }
}
