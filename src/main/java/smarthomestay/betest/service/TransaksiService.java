package smarthomestay.betest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import smarthomestay.betest.common.ResponseDTO;
import smarthomestay.betest.dto.*;
import smarthomestay.betest.dto.response.TransaksiResponse;
import smarthomestay.betest.model.*;
import smarthomestay.betest.repository.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static smarthomestay.betest.common.Checker.isNullStr;

@Service
public class TransaksiService {
    @Autowired
    private TransaksiRepository transaksiRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private AddFacilityRepository addFacilityRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private AddFacilityService addFacilityService;

    public ResponseEntity<?> saveTransaksi(MstTransaksiDTO requestDTO){
        if (requestDTO.getTransaksiId() == null){
            return createTransaksi(requestDTO);
        }
        return updateTransaksi(requestDTO);
    }
    private ResponseEntity<?> createTransaksi(MstTransaksiDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstTransaksi transaksiEntity = new MstTransaksi();
        MstTransaksi transaksi = transaksiRepo.findByTransaksiId(requestDTO.getTransaksiId());
        MstUser user = userRepo.findByUserId(requestDTO.getUserId());
        MstEmployee employee = employeeRepo.findByEmployeeId(requestDTO.getEmployeeId());
        MstRoom room = roomRepo.findByRoomId(requestDTO.getRoomId());
        MstAddFacility addFacility = addFacilityRepo.findByFacilityId(requestDTO.getFacilityId());
        if (user.getUserId() == null){
            response.setCode("204");
            response.setMessage("User Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (employee.getEmployeeId() == null){
            response.setCode("204");
            response.setMessage("Employee Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (room.getRoomId() == null){
            response.setCode("204");
            response.setMessage("Room Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (addFacility.getFacilityId() == null){
            response.setCode("204");
            response.setMessage("Facility Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (transaksi == null){
            transaksiEntity.setUserId(requestDTO.getUserId());
            transaksiEntity.setEmployeeId(requestDTO.getEmployeeId());
            transaksiEntity.setRoomId(requestDTO.getRoomId());
            transaksiEntity.setFacilityId(requestDTO.getFacilityId());
            transaksiEntity.setNumberOfRoom(requestDTO.getNumberOfRoom());
            transaksiEntity.setStatus(requestDTO.getStatus());
            transaksiEntity.setDateCheckin(requestDTO.getDateCheckin());
            transaksiEntity.setDateCheckout(requestDTO.getDateCheckout());
            transaksiEntity.setLengthOfStay(requestDTO.getLengthOfStay());
            //price room
            BigDecimal priceRoom = roomRepo.findByRoomId(requestDTO.getRoomId()).getPrice();
            BigDecimal priceFacility = addFacilityRepo.findByFacilityId(requestDTO.getFacilityId()).getPrice();

            //(priceRoom + priceFacility) * NumberofRoom * LengthOfStay
            transaksiEntity.setTotal(priceRoom.add(priceFacility).multiply(requestDTO.getNumberOfRoom()).multiply(requestDTO.getLengthOfStay()));

            transaksiRepo.save(transaksiEntity);
        }
        response.setCode("201");
        response.setData(transaksiEntity);
        response.setMessage("Pengajuan has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private ResponseEntity<?> updateTransaksi(MstTransaksiDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstTransaksi transaksiEntity = new MstTransaksi();
        MstTransaksi transaksiList = transaksiRepo.findByTransaksiId(requestDTO.getTransaksiId());
        MstUser user = userRepo.findByUserId(requestDTO.getUserId());
        MstEmployee employee = employeeRepo.findByEmployeeId(requestDTO.getEmployeeId());
        MstRoom room = roomRepo.findByRoomId(requestDTO.getRoomId());
        MstAddFacility addFacility = addFacilityRepo.findByFacilityId(requestDTO.getFacilityId());

        if (transaksiList == null){
            response.setCode("204");
            response.setMessage("data not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        MstTransaksi transaksi = transaksiRepo.findByTransaksiId(requestDTO.getTransaksiId());
        if (requestDTO.getTransaksiId() == null){
            transaksiEntity.setTransaksiId(transaksi.getTransaksiId());
        }else{
            transaksiEntity.setTransaksiId(requestDTO.getTransaksiId());
        }
        if (requestDTO.getUserId() == null){
            transaksiEntity.setUserId(user.getUserId());
        } else {
            transaksiEntity.setUserId(requestDTO.getUserId());
        }
        if (requestDTO.getEmployeeId() == null){
            transaksiEntity.setEmployeeId(employee.getEmployeeId());
        } else {
            transaksiEntity.setEmployeeId(requestDTO.getEmployeeId());
        }
        if (requestDTO.getRoomId() == null){
            transaksiEntity.setRoomId(room.getRoomId());
        } else {
            transaksiEntity.setRoomId(requestDTO.getRoomId());
        }
        if (requestDTO.getFacilityId() == null){
            transaksiEntity.setFacilityId(addFacility.getFacilityId());
        } else {
            transaksiEntity.setFacilityId(requestDTO.getFacilityId());
        }
        if (isNull(requestDTO.getNumberOfRoom())){
            transaksiEntity.setNumberOfRoom(requestDTO.getNumberOfRoom());
        }else{
            transaksiEntity.setNumberOfRoom(requestDTO.getNumberOfRoom());
        }
        if (isNullStr(requestDTO.getStatus())){
            transaksiEntity.setStatus(requestDTO.getStatus());
        } else {
            transaksiEntity.setStatus(transaksi.getStatus());
        }
        if (isNull(requestDTO.getDateCheckin())){
            transaksiEntity.setDateCheckin(requestDTO.getDateCheckin());
        } else {
            transaksiEntity.setDateCheckin(requestDTO.getDateCheckin());
        }
        if (isNull(requestDTO.getDateCheckout())){
            transaksiEntity.setDateCheckout(requestDTO.getDateCheckout());
        } else {
            transaksiEntity.setDateCheckout(requestDTO.getDateCheckout());
        }
        if (isNull(requestDTO.getLengthOfStay())){
            transaksiEntity.setLengthOfStay(requestDTO.getLengthOfStay());
        } else {
            transaksiEntity.setLengthOfStay(requestDTO.getLengthOfStay());
        }
        //price room
        BigDecimal priceRoom = roomRepo.findByRoomId(requestDTO.getRoomId()).getPrice();
        BigDecimal priceFacility = addFacilityRepo.findByFacilityId(requestDTO.getFacilityId()).getPrice();

        //(priceRoom + priceFacility) * NumberofRoom * LengthOfStay
        transaksiEntity.setTotal(priceRoom.add(priceFacility).multiply(requestDTO.getNumberOfRoom()).multiply(requestDTO.getLengthOfStay()));

        transaksiRepo.save(transaksiEntity);

        response.setCode("201");
        response.setData(transaksiEntity);
        response.setMessage("Transaksi has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAll(){
        ResponseDTO response = new ResponseDTO();
        List<MstTransaksi> transaksiEntity = transaksiRepo.findAll();
        List<TransaksiResponse> responses = new ArrayList<>();

        for (MstTransaksi transaksi:transaksiEntity){
            TransaksiResponse resp = new TransaksiResponse();
            resp.setTransaksiId(transaksi.getTransaksiId());
            resp.setUserId(transaksi.getUserId());
            resp.setEmployeeId(transaksi.getEmployeeId());
            resp.setRoomId(transaksi.getRoomId());
            resp.setFacilityId(transaksi.getFacilityId());
            resp.setNumberOfRoom(transaksi.getNumberOfRoom());
            resp.setStatus(transaksi.getStatus());
            resp.setDateCheckin(transaksi.getDateCheckin());
            resp.setDateCheckout(transaksi.getDateCheckout());
            resp.setLengthOfStay(transaksi.getLengthOfStay());
            resp.setTotal(transaksi.getTotal());

            MstUser user = userRepo.findByUserId(resp.getUserId());
            MstUserDTO userDTO = new MstUserDTO();
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setUserEmail(user.getUserEmail());
            userDTO.setUserPhone(user.getUserPhone());

            MstEmployee employee = employeeRepo.findByEmployeeId(resp.getEmployeeId());
            MstEmployeeDTO employeeDTO = new MstEmployeeDTO();
            employeeDTO.setFirstName(employee.getFirstName());
            employeeDTO.setLastName(employee.getLastName());

            MstRoom room = roomRepo.findByRoomId(resp.getRoomId());
            MstRoomDTO roomDTO = new MstRoomDTO();
            roomDTO.setRoomName(room.getRoomName());
            roomDTO.setPrice(room.getPrice());

            MstAddFacility addFacility = addFacilityRepo.findByFacilityId(resp.getFacilityId());
            MstAddFacilityDTO addFacilityDTO = new MstAddFacilityDTO();
            addFacilityDTO.setAdditionalFacilities(addFacility.getAdditionalFacilities());
            addFacilityDTO.setPrice(addFacility.getPrice());

            resp.setUser(userDTO);
            resp.setEmployee(employeeDTO);
            resp.setRoom(roomDTO);
            resp.setAddFacility(addFacilityDTO);

            responses.add(resp);
        }
        response.setCode("200");
        response.setData(responses);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByTransaksiId(Long transaksiId){
        ResponseDTO response = new ResponseDTO();
        MstTransaksi transaksi = transaksiRepo.findByTransaksiId(transaksiId);
        TransaksiResponse resp = new TransaksiResponse();
        if(transaksi==null){
            response.setCode("400");
            response.setMessage("Err:Transaksi tidak ditemukan");
            response.setData(null);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        resp.setTransaksiId(transaksi.getTransaksiId());
        resp.setUserId(transaksi.getUserId());
        resp.setEmployeeId(transaksi.getEmployeeId());
        resp.setRoomId(transaksi.getRoomId());
        resp.setFacilityId(transaksi.getFacilityId());
        resp.setNumberOfRoom(transaksi.getNumberOfRoom());
        resp.setStatus(transaksi.getStatus());
        resp.setDateCheckin(transaksi.getDateCheckin());
        resp.setDateCheckout(transaksi.getDateCheckout());
        resp.setLengthOfStay(transaksi.getLengthOfStay());
        resp.setTotal(transaksi.getTotal());

        MstUser user = userRepo.findByUserId(resp.getUserId());
        MstUserDTO userDTO = new MstUserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUserEmail(user.getUserEmail());
        userDTO.setUserPhone(user.getUserPhone());

        MstEmployee employee = employeeRepo.findByEmployeeId(resp.getEmployeeId());
        MstEmployeeDTO employeeDTO = new MstEmployeeDTO();
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());

        MstRoom room = roomRepo.findByRoomId(resp.getRoomId());
        MstRoomDTO roomDTO = new MstRoomDTO();
        roomDTO.setRoomName(room.getRoomName());
        roomDTO.setPrice(room.getPrice());

        MstAddFacility addFacility = addFacilityRepo.findByFacilityId(resp.getFacilityId());
        MstAddFacilityDTO addFacilityDTO = new MstAddFacilityDTO();
        addFacilityDTO.setAdditionalFacilities(addFacility.getAdditionalFacilities());
        addFacilityDTO.setPrice(addFacility.getPrice());

        resp.setUser(userDTO);
        resp.setEmployee(employeeDTO);
        resp.setRoom(roomDTO);
        resp.setAddFacility(addFacilityDTO);

        response.setCode("200");
        response.setData(resp);
        response.setMessage("Get Data By Transaksi Id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
