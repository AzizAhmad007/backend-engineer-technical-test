package smarthomestay.betest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthomestay.betest.dto.MstRoomDTO;
import smarthomestay.betest.dto.MstTransaksiDTO;
import smarthomestay.betest.service.TransaksiService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/transaksi")
public class TransaksiController {
    @Autowired
    private TransaksiService transaksiService;

    @PostMapping("/save")
    public ResponseEntity<?> saveTransaksi(@RequestBody MstTransaksiDTO request){
        return transaksiService.saveTransaksi(request);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(){
        return transaksiService.getAll();
    }

    @GetMapping("/get_by/{transaksiId}")
    public ResponseEntity<?> getByTransaksiId(@PathVariable Long transaksiId){
        return transaksiService.getByTransaksiId(transaksiId);
    }
}
