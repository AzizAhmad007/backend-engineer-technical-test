package smarthomestay.betest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import smarthomestay.betest.common.ResponseDTO;
import smarthomestay.betest.dto.MstEmployeeDTO;
import smarthomestay.betest.model.MstEmployee;
import smarthomestay.betest.model.MstUser;
import smarthomestay.betest.repository.EmployeeRepository;

import java.security.MessageDigest;
import java.util.List;

import static smarthomestay.betest.common.Checker.isNullStr;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepo;

    public ResponseEntity<?> saveEmployee(MstEmployeeDTO requestDTO){
        if (requestDTO.getEmployeeId() == null){
            return createEmployee(requestDTO);
        }
        return updateEmployee(requestDTO);
    }

    private ResponseEntity<?> createEmployee(MstEmployeeDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstEmployee employeeEntity = new MstEmployee();
        MstEmployee employee = employeeRepo.findByEmployeeId(requestDTO.getEmployeeId());
        if (employee == null){
            if (isNullStr(requestDTO.getFirstName())){
                if (isNullStr(requestDTO.getLastName())){
                    // Cek format email
                    if (!isValidEmail(requestDTO.getEmployeeEmail())){
                        response.setCode("204");
                        response.setMessage("Email tidak valid");
                        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    // Cek apakah email sudah ada di database
                    MstEmployee existingUser = employeeRepo.findByEmail(requestDTO.getEmployeeEmail());
                    if (existingUser != null){
                        response.setCode("204");
                        response.setMessage("Email sudah ada");
                        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    // Cek data tidak boleh null atau kosong
                    if (isNullStr(requestDTO.getEmployeeEmail())){
                        if (isNullStr(requestDTO.getPassword())){
                            if (isNullStr(requestDTO.getEmployeePhone())){
                                employeeEntity.setFirstName(requestDTO.getFirstName());
                                employeeEntity.setLastName(requestDTO.getLastName());
                                employeeEntity.setEmployeeEmail(requestDTO.getEmployeeEmail());
                                employeeEntity.setPassword(passEncript(requestDTO.getPassword()));
                                employeeEntity.setEmployeePhone(requestDTO.getEmployeePhone());

                                employeeRepo.save(employeeEntity);
                                response.setCode("201");
                                response.setData(employeeEntity);
                                response.setMessage("User has been saved successfully");
                                return new ResponseEntity<>(response, HttpStatus.CREATED);
                            }
                            response.setCode("204");
                            response.setMessage("Employee Phone cannot be empty");
                            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                        response.setCode("204");
                        response.setMessage("Password Phone cannot be empty");
                        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    response.setCode("204");
                    response.setMessage("Employee Email cannot be empty");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                response.setCode("204");
                response.setMessage("Last Name cannot be empty");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setCode("204");
            response.setMessage("First Name cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("409");
        response.setMessage("Data already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private boolean isValidEmail(String employeeEmail){
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return employeeEmail.matches(emailRegex);
    }

    private String passEncript(String pass) {
        MessageDigest md;
        String passEnc;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte[] bytes = md.digest();
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            passEnc = s.toString();
        } catch (Exception e) {
            return "Password Encryption Error";
        }
        return passEnc;
    }

    private ResponseEntity<?> updateEmployee(MstEmployeeDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstEmployee employeeEntity = new MstEmployee();
        MstEmployee employeeList = employeeRepo.findByEmployeeId(requestDTO.getEmployeeId());

        if (employeeList == null){
            response.setCode("204");
            response.setMessage("data not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        MstEmployee employee = employeeRepo.findByEmployeeId(requestDTO.getEmployeeId());
        if (requestDTO.getEmployeeId() == null){
            employeeEntity.setEmployeeId(employee.getEmployeeId());
        } else {
            employeeEntity.setEmployeeId(requestDTO.getEmployeeId());
        }
        if (isNullStr(requestDTO.getFirstName())){
            employeeEntity.setFirstName(requestDTO.getFirstName());
        } else {
            employeeEntity.setFirstName(employee.getFirstName());
        }
        if (isNullStr(requestDTO.getLastName())){
            employeeEntity.setLastName(requestDTO.getLastName());
        } else {
            employeeEntity.setLastName(employee.getLastName());
        }
        if (isNullStr(requestDTO.getEmployeeEmail())){
            employeeEntity.setEmployeeEmail(requestDTO.getEmployeeEmail());
        } else {
            employeeEntity.setEmployeeEmail(employee.getEmployeeEmail());
        }
        if (isNullStr(requestDTO.getPassword())){
            employeeEntity.setPassword(passEncript(requestDTO.getPassword()));
        } else {
            employeeEntity.setPassword(employee.getPassword());
        }
        if (isNullStr(requestDTO.getEmployeePhone())){
            employeeEntity.setEmployeePhone(requestDTO.getEmployeePhone());
        } else {
            employeeEntity.setEmployeePhone(employee.getEmployeePhone());
        }
        employeeRepo.save(employeeEntity);

        response.setCode("201");
        response.setData(null);
        response.setMessage("Employee has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<?> delete(Long employeeId){
        ResponseDTO response = new ResponseDTO();
        MstEmployee employee = employeeRepo.findByEmployeeId(employeeId);
        if (employee == null){
            response.setCode("204");
            response.setMessage("Employee ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        employeeRepo.delete(employee);
        response.setCode("200");
        response.setData(null);
        response.setMessage("Employee ID successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getByEmployeeId(Long employeeId){
        ResponseDTO response = new ResponseDTO();
        MstEmployee employee = employeeRepo.findByEmployeeId(employeeId);
        if (employee == null){
            response.setCode("204");
            response.setMessage("User ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("200");
        response.setData(employee);
        response.setMessage("Get Data By User Id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getAll(){
        ResponseDTO response = new ResponseDTO();
        List<MstEmployee> employeerList = employeeRepo.findAll();

        response.setCode("200");
        response.setData(employeerList);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
