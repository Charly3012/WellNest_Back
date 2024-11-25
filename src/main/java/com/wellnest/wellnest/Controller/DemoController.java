package com.wellnest.wellnest.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {

    @GetMapping("holaMundo")
    public ResponseEntity<Map<String, String>> holaMundo()
    {
        Map<String, String> response = new HashMap<>();
        response.put("message", "WEBOS");
        return ResponseEntity.ok(response);
    }
}
