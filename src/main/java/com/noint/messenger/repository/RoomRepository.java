package com.noint.messenger.repository;

import com.noint.messenger.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    public List<Room> findAllBySeqIn(List<Long> seq);
}
