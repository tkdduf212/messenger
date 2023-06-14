package com.noint.messenger.dto;

import com.noint.messenger.entity.Room;
import com.noint.messenger.entity.UserRoom;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class RoomListDTO {
    @Data
    public static class RoomListInfo {
        private Long roomSeq;
        private String roomId;
        private List<RoomMemberInfo> roomMemberInfoList;

//        public static List<RoomListInfo> newBuilder(List<Room> roomList){
//            List<RoomListInfo> dtoList = new ArrayList<>();
//            for (Room room: roomList) {
//                RoomListInfo dto = new RoomListInfo();
//                dto.setRoomSeq(room.getSeq());
//                dto.setRoomId(room.getRoomId());
//                dto.setRoomMemberInfoList();
//            }
//        }
    }

    @Data
    public static class RoomMemberInfo{
        private String name;
        private Long seq;

//        public static List<RoomMemberInfo> newBuilder(List<UserRoom> participants) {
//            List<RoomMemberInfo> dtoList = new ArrayList<>();
//            for (UserRoom user : participants) {
//                RoomMemberInfo dto = new RoomMemberInfo();
//                dto.setName(user.getUserSeq());
//                dto.setSeq(user.getUserSeq());
//            }
//            return
//        }
    }
}
