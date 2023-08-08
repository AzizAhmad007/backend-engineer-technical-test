package smarthomestay.betest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import smarthomestay.betest.common.ResponseDTO;
import smarthomestay.betest.dto.MstRoomDTO;
import smarthomestay.betest.model.MstRoom;
import smarthomestay.betest.repository.RoomRepository;
import java.util.List;

import static java.util.Objects.isNull;
import static smarthomestay.betest.common.Checker.isNullStr;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepo;

    public ResponseEntity<?> saveRoom(MstRoomDTO requestDTO){
        if (requestDTO.getRoomId() == null){
            return createRoom(requestDTO);
        }
       return updateRoom(requestDTO);
    }

    private ResponseEntity<?> createRoom(MstRoomDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstRoom roomEntity = new MstRoom();
        MstRoom room = roomRepo.findByRoomId(requestDTO.getRoomId());
        if (room == null){
            if (isNullStr(requestDTO.getRoomName())){
                if (isNullStr(""+requestDTO.getPrice())){
                    roomEntity.setRoomName(requestDTO.getRoomName());
                    roomEntity.setPrice(requestDTO.getPrice());

                    roomRepo.save(roomEntity);

                    response.setCode("201");
                    response.setData(roomEntity);
                    response.setMessage("Room has been saved successfully");
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                }
                response.setCode("204");
                response.setMessage("Price cannot be empty");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setCode("204");
            response.setMessage("Room name cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("409");
        response.setMessage("Data Room already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    public ResponseEntity<?> updateRoom(MstRoomDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstRoom roomEntity = new MstRoom();
        MstRoom roomList = roomRepo.findByRoomId(requestDTO.getRoomId());
        if (roomList == null){
            response.setCode("204");
            response.setMessage("data not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        MstRoom room = roomRepo.findByRoomId(requestDTO.getRoomId());
        if (requestDTO.getRoomId() == null){
            roomEntity.setRoomId(room.getRoomId());
        } else {
            roomEntity.setRoomId(requestDTO.getRoomId());
        }
        if (isNullStr(requestDTO.getRoomName())){
            roomEntity.setRoomName(requestDTO.getRoomName());
        } else {
            roomEntity.setRoomName(requestDTO.getRoomName());
        }
        if (isNull(requestDTO.getPrice())){
            roomEntity.setPrice(requestDTO.getPrice());
        } else {
            roomEntity.setPrice(requestDTO.getPrice());
        }
        roomRepo.save(roomEntity);

        response.setCode("201");
        response.setData(roomEntity);
        response.setMessage("Room has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteRoom(Long roomId){
        ResponseDTO response = new ResponseDTO();
        MstRoom room = roomRepo.findByRoomId(roomId);
        if (room == null){
            response.setCode("204");
            response.setMessage("Room ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        roomRepo.delete(room);
        response.setCode("200");
        response.setData(null);
        response.setMessage("Room id successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllRoom(){
        ResponseDTO response = new ResponseDTO();
        List<MstRoom> roomList = roomRepo.findAll();

        response.setCode("200");
        response.setData(roomList);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByRoomId(Long roomId){
        ResponseDTO response = new ResponseDTO();
        MstRoom room = roomRepo.findByRoomId(roomId);
        if (room == null){
            response.setCode("204");
            response.setMessage("Room ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("200");
        response.setData(room);
        response.setMessage("Get Data By Room Id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
