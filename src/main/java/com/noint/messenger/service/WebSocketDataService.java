package com.noint.messenger.service;

import com.noint.messenger.dto.RoomListDTO;
import com.noint.messenger.entity.Member;
import com.noint.messenger.entity.Room;
import com.noint.messenger.entity.UserRoom;
import com.noint.messenger.repository.MemberRepository;
import com.noint.messenger.repository.RoomRepository;
import com.noint.messenger.repository.UserRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WebSocketDataService {
    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;
    private final UserRoomRepository userRoomRepository;

    public Member getData(Long seq) {
        Member member = memberRepository.findById(seq).get();
        List<UserRoom> userRoomList = userRoomRepository.findByUserSeqAndDelDateIsNull(member.getSeq());
        Map<Long, List<UserRoom>> groupByRoomSeq = userRoomList.stream().collect(Collectors.groupingBy(UserRoom::getRoomSeq));
        List<Room> roomInfoList = roomRepository.findAllBySeqIn(new ArrayList<>(groupByRoomSeq.keySet()));
        List<RoomListDTO.RoomListInfo> dataList = new ArrayList<>();
//        for (Room info : roomInfoList) {
//            RoomListDTO.RoomListInfo room = new RoomListDTO.RoomListInfo();
//            room.setRoomId(info.getRoomId());
//            room.setRoomSeq(info.getSeq());
//            room.setRoomMemberInfoList();
//        }
        RoomListDTO.RoomListInfo roomData = new RoomListDTO.RoomListInfo();
        return member;
    }
}
