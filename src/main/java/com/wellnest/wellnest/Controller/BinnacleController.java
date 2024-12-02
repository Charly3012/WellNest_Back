package com.wellnest.wellnest.Controller;

import com.wellnest.wellnest.Models.Request.Binnacle.EditBinnacleRequest;
import com.wellnest.wellnest.Models.Request.Binnacle.InsertBinnacleRequest;
import com.wellnest.wellnest.Models.Responses.Binnacle.GetBinnacleResponse;
import com.wellnest.wellnest.Services.BinnacleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/binnacle")
public class BinnacleController {

    @Autowired
    private BinnacleService binnacleService;

    @GetMapping("{idBinnacle}")
    public ResponseEntity<GetBinnacleResponse> getBinnacles(@PathVariable int idBinnacle) {
        return ResponseEntity.ok(binnacleService.getBinnacleById(idBinnacle));
    }

    @GetMapping()
    public ResponseEntity<List<GetBinnacleResponse>> getBinnacles() {
        return ResponseEntity.ok(binnacleService.getAllBinnacles());
    }

    @GetMapping("getUserBinnacles")
    public ResponseEntity<List<GetBinnacleResponse>> getUserBinnacles(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(binnacleService.getUserBinnacles(token));
    }

    @PostMapping("insertBinnacle")
    public ResponseEntity<Void> insertBinnacle(@RequestBody InsertBinnacleRequest insertBinnacleRequest,
                                               @RequestHeader("Authorization") String token) {
        if (binnacleService.insertBinnacle(token ,insertBinnacleRequest)){
            return ResponseEntity.created(null).build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{idBinnacle}")
    public ResponseEntity<Void> deleteBinnacle(@RequestHeader("Authorization") String token,
                                               @PathVariable int idBinnacle) {
        if (binnacleService.deleteBinnacle(token, idBinnacle)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{idBinnacle}")
    public ResponseEntity<Void> modifyBinnacle(@RequestHeader("Authorization") String token,
                                               @PathVariable long idBinnacle,
                                               @RequestBody EditBinnacleRequest editBinnacleRequest) {
        if (binnacleService.modifyBinnacle(token, idBinnacle, editBinnacleRequest)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

}
