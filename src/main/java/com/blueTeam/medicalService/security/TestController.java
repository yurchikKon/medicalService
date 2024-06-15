package com.blueTeam.medicalService.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medicalService/demo-controller")
public class TestController {

    @GetMapping
    public ResponseEntity <String> helloWorld() {
        return ResponseEntity.ok("Hello World !!!");
    }
}
