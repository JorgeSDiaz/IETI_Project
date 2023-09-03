package org.ieti.proyecto.controller.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthContoller {
    @GetMapping
    public String checkAPI(){
        return "<h1>The API is working ok!</h1>";
    }
}
