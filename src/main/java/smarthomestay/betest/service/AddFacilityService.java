package smarthomestay.betest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import smarthomestay.betest.common.ResponseDTO;
import smarthomestay.betest.dto.MstAddFacilityDTO;
import smarthomestay.betest.model.MstAddFacility;
import smarthomestay.betest.repository.AddFacilityRepository;

import java.util.List;

import static java.util.Objects.isNull;
import static smarthomestay.betest.common.Checker.isNullStr;

@Service
public class AddFacilityService {
    @Autowired
    private AddFacilityRepository addFacilityRepo;

    public ResponseEntity<?> saveFacility(MstAddFacilityDTO requestDTO){
        if (requestDTO.getFacilityId() == null){
            return createFacility(requestDTO);
        }
        return updateFacility(requestDTO);
    }

    private ResponseEntity<?> createFacility(MstAddFacilityDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstAddFacility facilityEntity = new MstAddFacility();
        MstAddFacility addFacility = addFacilityRepo.findByFacilityId(requestDTO.getFacilityId());
        if (addFacility == null){
            if (isNullStr(requestDTO.getAdditionalFacilities())){
                if (isNullStr(""+requestDTO.getPrice())){
                    facilityEntity.setAdditionalFacilities(requestDTO.getAdditionalFacilities());
                    facilityEntity.setPrice(requestDTO.getPrice());

                    addFacilityRepo.save(facilityEntity);
                    response.setCode("201");
                    response.setData(facilityEntity);
                    response.setMessage("Room has been saved successfully");
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                }
                response.setCode("204");
                response.setMessage("Additional Facilities cannot be empty");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setCode("204");
            response.setMessage("Price cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("409");
        response.setMessage("Data Additional Facilities already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private ResponseEntity<?> updateFacility(MstAddFacilityDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstAddFacility facilityEntity = new MstAddFacility();
        MstAddFacility facilityList = addFacilityRepo.findByFacilityId(requestDTO.getFacilityId());
        if (facilityList == null){
            response.setCode("204");
            response.setMessage("data not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        MstAddFacility addFacility = addFacilityRepo.findByFacilityId(requestDTO.getFacilityId());
        if (requestDTO.getFacilityId() == null){
            facilityEntity.setFacilityId(addFacility.getFacilityId());
        } else {
            facilityEntity.setFacilityId(requestDTO.getFacilityId());
        }
        if (isNullStr(requestDTO.getAdditionalFacilities())){
            facilityEntity.setAdditionalFacilities(requestDTO.getAdditionalFacilities());
        } else {
            facilityEntity.setAdditionalFacilities(requestDTO.getAdditionalFacilities());
        }
        if (isNull(requestDTO.getPrice())){
            facilityEntity.setPrice(requestDTO.getPrice());
        } else {
            facilityEntity.setPrice(requestDTO.getPrice());
        }
        addFacilityRepo.save(facilityEntity);

        response.setCode("201");
        response.setData(facilityEntity);
        response.setMessage("Add Facility has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<?> delete(Long facilityId){
        ResponseDTO response = new ResponseDTO();
        MstAddFacility addFacility = addFacilityRepo.findByFacilityId(facilityId);
        if (addFacility == null){
            response.setCode("204");
            response.setMessage("Add Facility ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        addFacilityRepo.delete(addFacility);
        response.setCode("200");
        response.setData(null);
        response.setMessage("Add Facility Id successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getAll(){
        ResponseDTO response = new ResponseDTO();
        List<MstAddFacility> facilityList = addFacilityRepo.findAll();

        response.setCode("200");
        response.setData(facilityList);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByFacilityId(Long facilityId){
        ResponseDTO response = new ResponseDTO();
        MstAddFacility addFacility = addFacilityRepo.findByFacilityId(facilityId);
        if (addFacility == null){
            response.setCode("204");
            response.setMessage("Add Facility ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("200");
        response.setData(addFacility);
        response.setMessage("Get Data By Add Facility Id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
