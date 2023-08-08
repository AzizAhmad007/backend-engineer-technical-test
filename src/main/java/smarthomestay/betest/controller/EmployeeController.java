package smarthomestay.betest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthomestay.betest.dto.MstEmployeeDTO;
import smarthomestay.betest.dto.MstUserDTO;
import smarthomestay.betest.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<?> saveEmployee(@RequestBody MstEmployeeDTO request){
        return employeeService.saveEmployee(request);
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<?> delete(@PathVariable Long employeeId){
        return employeeService.delete(employeeId);
    }

    @GetMapping("/get_by/{employeeId}")
    public ResponseEntity<?> getByEmployeeId(@PathVariable Long employeeId){
        return employeeService.getByEmployeeId(employeeId);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(){
        return employeeService.getAll();
    }
}
