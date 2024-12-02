package com.wellnest.wellnest.Controller;

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

    @PostMapping("insertBinnacle")
    public ResponseEntity<Void> insertBinnacle(@RequestBody InsertBinnacleRequest insertBinnacleRequest) {
        if (binnacleService.insertBinnacle(insertBinnacleRequest)){
            return ResponseEntity.created(null).build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

}
