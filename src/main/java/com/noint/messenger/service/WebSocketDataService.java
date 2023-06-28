package com.noint.messenger.service;

import com.noint.messenger.dto.RoomListDTO;
import com.noint.messenger.entity.Member;
import com.noint.messenger.entity.Room;
import com.noint.messenger.entity.UserRoom;
import com.noint.messenger.repository.MemberRepository;
import com.noint.messenger.repository.RoomRepository;
import com.noint.messenger.repository.UserRoomRepository;
import com.noint.messenger.repository.WebSocketChatRepository;
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
    private final WebSocketChatRepository webSocketChatRepository;

    public List<RoomListDTO.ParticipatingRoomInfo> getData(Long seq) {
        List<RoomListDTO.ParticipatingRoomInfo> roomListInfos = webSocketChatRepository.roomListInfo(seq);
        return roomListInfos;
    }
}
