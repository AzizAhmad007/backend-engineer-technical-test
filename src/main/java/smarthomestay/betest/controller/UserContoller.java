package smarthomestay.betest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthomestay.betest.dto.MstAddFacilityDTO;
import smarthomestay.betest.dto.MstUserDTO;
import smarthomestay.betest.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserContoller {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody MstUserDTO request){
        return userService.saveUser(request);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> delete(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }

    @GetMapping("/get_by/{userId}")
    public ResponseEntity<?> getByFacilityId(@PathVariable Long userId){
        return userService.getByUserId(userId);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(){
        return userService.getAllUser();
    }
}
