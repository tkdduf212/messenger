package com.noint.messenger.repository;

import com.noint.messenger.entity.UserRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoomRepository extends JpaRepository<UserRoom, Long> {
    @Query(value = "SELECT T2.seq, T2.room_seq, T2.user_seq, T2.reg_date, T2.del_date" +
            " FROM user_room_tb T1" +
            " INNER JOIN user_room_tb T2 ON T1.room_seq = T2.room_seq AND T1.user_seq != T2.user_seq" +
            " WHERE T1.user_seq= ? AND T1.del_date IS NULL", nativeQuery = true)
    public List<UserRoom> findByUserSeqAndDelDateIsNull(Long userSeq);
}
