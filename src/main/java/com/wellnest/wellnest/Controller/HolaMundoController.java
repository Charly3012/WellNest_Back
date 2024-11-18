package com.wellnest.wellnest.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webos")
public class HolaMundoController {

    @GetMapping(value = "webos2")
    public String getHola()
    {
        return "Hola Mundo";
    }

}

