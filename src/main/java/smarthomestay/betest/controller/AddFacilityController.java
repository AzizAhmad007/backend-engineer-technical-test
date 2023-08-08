package smarthomestay.betest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthomestay.betest.dto.MstAddFacilityDTO;
import smarthomestay.betest.service.AddFacilityService;

@RestController
@RequestMapping("/api/v1/facility")
public class AddFacilityController {
    @Autowired
    private AddFacilityService addFacilityService;

    @PostMapping("/save")
    public ResponseEntity<?> saveFacility(@RequestBody MstAddFacilityDTO request){
        return addFacilityService.saveFacility(request);
    }

    @DeleteMapping("/delete/{facilityId}")
    public ResponseEntity<?> delete(@PathVariable Long facilityId){
        return addFacilityService.delete(facilityId);
    }

    @GetMapping("/get_by/{facilityId}")
    public ResponseEntity<?> getByFacilityId(@PathVariable Long facilityId){
        return addFacilityService.getByFacilityId(facilityId);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(){
        return addFacilityService.getAll();
    }
}
