package com.noint.messenger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class RoomListDTO {
    @Data
    @AllArgsConstructor
    public static class ParticipatingRoomInfo {
        //참여중인 방들의 정보
        private Long roomSeq;
        private String roomId;
        private String names; // 해당 방에 참여중인 상대방(방 이름용도로 사용)
        private Date regDate;
    }
}
