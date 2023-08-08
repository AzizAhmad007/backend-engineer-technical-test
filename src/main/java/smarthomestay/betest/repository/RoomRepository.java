package smarthomestay.betest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import smarthomestay.betest.model.MstRoom;

import java.util.List;

@Repository
public interface RoomRepository extends PagingAndSortingRepository<MstRoom, Long> {
    @Query(value = "select ms.* from mst_room ms where ms.room_id= :roomId order by ms.room_id asc limit 1",nativeQuery = true)
    MstRoom findByRoomId(@Param("roomId") Long roomId);

    @Query(value = "select ms.* from mst_room ms order by ms.room_id asc ",nativeQuery = true)
    List<MstRoom> findAll();
}
