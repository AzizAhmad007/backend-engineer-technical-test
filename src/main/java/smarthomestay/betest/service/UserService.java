package smarthomestay.betest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import smarthomestay.betest.common.ResponseDTO;
import smarthomestay.betest.dto.MstUserDTO;
import smarthomestay.betest.model.MstUser;
import smarthomestay.betest.repository.UserRepository;

import java.security.MessageDigest;
import java.util.List;

import static smarthomestay.betest.common.Checker.isNullStr;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public ResponseEntity<?> saveUser(MstUserDTO requestDTO){
        if (requestDTO.getUserId() == null){
            return createUser(requestDTO);
        }
            return updateUser(requestDTO);
    }

    public ResponseEntity<?> deleteUser(Long userId){
        ResponseDTO response = new ResponseDTO();
        MstUser user = userRepo.findByUserId(userId);
        if (user == null){
            response.setCode("204");
            response.setMessage("User ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        userRepo.delete(user);
        response.setCode("200");
        response.setData(null);
        response.setMessage("User id successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<?> createUser(MstUserDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstUser userEntity = new MstUser();
        MstUser user = userRepo.findByUserId(requestDTO.getUserId());
        if (user == null){
            if (isNullStr(requestDTO.getFirstName())){
                if (isNullStr(requestDTO.getLastName())){
                    // Cek format email
                    if (!isValidEmail(requestDTO.getUserEmail())){
                        response.setCode("204");
                        response.setMessage("Email tidak valid");
                        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    // Cek apakah email sudah ada di database
                    MstUser existingUser = userRepo.findByEmail(requestDTO.getUserEmail());
                    if (existingUser != null){
                        response.setCode("204");
                        response.setMessage("Email sudah ada");
                        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    // Cek data tidak boleh null atau kosong
                    if (isNullStr(requestDTO.getUserEmail())){
                        if (isNullStr(requestDTO.getPassword())){
                            if (isNullStr(requestDTO.getUserPhone())){
                                userEntity.setFirstName(requestDTO.getFirstName());
                                userEntity.setLastName(requestDTO.getLastName());
                                userEntity.setUserEmail(requestDTO.getUserEmail());
                                userEntity.setPassword(passEncript(requestDTO.getPassword()));
                                userEntity.setUserPhone(requestDTO.getUserPhone());

                                userRepo.save(userEntity);
                                response.setCode("201");
                                response.setData(null);
                                response.setMessage("User has been saved successfully");
                                return new ResponseEntity<>(response, HttpStatus.CREATED);
                            }
                            response.setCode("204");
                            response.setMessage("User Phone cannot be empty");
                            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                        response.setCode("204");
                        response.setMessage("Password cannot be empty");
                        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    response.setCode("204");
                    response.setMessage("User Email cannot be empty");
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

    private ResponseEntity<?> updateUser(MstUserDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstUser userEntity = new MstUser();
        MstUser userList = userRepo.findByUserId(requestDTO.getUserId());

        if (userList == null){
            response.setCode("204");
            response.setMessage("data not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        MstUser user = userRepo.findByUserId(requestDTO.getUserId());
        if (requestDTO.getUserId() == null){
            userEntity.setUserId(user.getUserId());
        } else {
            userEntity.setUserId(requestDTO.getUserId());
        }
        if (isNullStr(requestDTO.getFirstName())){
            userEntity.setFirstName(requestDTO.getFirstName());
        } else {
            userEntity.setFirstName(user.getFirstName());
        }
        if (isNullStr(requestDTO.getLastName())){
            userEntity.setLastName(requestDTO.getLastName());
        } else {
            userEntity.setLastName(user.getLastName());
        }
        if (isNullStr(requestDTO.getUserEmail())){
            userEntity.setUserEmail(requestDTO.getUserEmail());
        } else {
            userEntity.setUserEmail(user.getUserEmail());
        }
        if (isNullStr(requestDTO.getPassword())){
            userEntity.setPassword(passEncript(requestDTO.getPassword()));
        } else {
            userEntity.setPassword(user.getPassword());
        }
        if (isNullStr(requestDTO.getUserPhone())){
            userEntity.setUserPhone(requestDTO.getUserPhone());
        } else {
            userEntity.setUserPhone(user.getUserPhone());
        }
        userRepo.save(userEntity);

        response.setCode("201");
        response.setData(null);
        response.setMessage("User has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private boolean isValidEmail(String userEmail){
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return userEmail.matches(emailRegex);
    }

    private String passEncript(String pass){
        MessageDigest md;
        String passEnc;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte[] bytes = md.digest();
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            passEnc = s.toString();
        } catch (Exception e) {
            return "Password Encryption Error";
        }
        return passEnc;
    }

    public ResponseEntity<?> getByUserId(Long userId){
        ResponseDTO response = new ResponseDTO();
        MstUser user = userRepo.findByUserId(userId);
        if (user == null){
            response.setCode("204");
            response.setMessage("User ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("200");
        response.setData(user);
        response.setMessage("Get Data By User Id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllUser(){
        ResponseDTO response = new ResponseDTO();
        List<MstUser> userList = userRepo.findAll();

        response.setCode("200");
        response.setData(userList);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
