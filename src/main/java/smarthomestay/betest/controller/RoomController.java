package smarthomestay.betest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthomestay.betest.dto.MstRoomDTO;
import smarthomestay.betest.service.RoomService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/save")
    public ResponseEntity<?> saveRoom(@RequestHeader Map<String,String> header, @RequestBody MstRoomDTO request){
        return roomService.saveRoom(request);
    }

    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long roomId){
        return roomService.deleteRoom(roomId);
    }

    @GetMapping("/get_by/{roomId}")
    public ResponseEntity<?> getByRoomId(@PathVariable Long roomId){
        return roomService.getByRoomId(roomId);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAllRoom(){
        return roomService.getAllRoom();
    }
}
