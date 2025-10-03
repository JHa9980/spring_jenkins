package com.skala.springbootsample.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skala.springbootsample.dto.HelloResponse;

@RestController
// @RequestMapping("/api")
public class HelloController {
    
    // @GetMapping("/hello")
    // public ResponseEntity<Map<String, String>> helloWorld() {
    //     Map<String, String> response = new HashMap();
    //     response.put("message", "Hellow World");
    //     return new ResponseEntity<>(response, HttpStatus.OK);
    // }
    
    @GetMapping("/hello")
    public HelloResponse hello() {
     HelloResponse response = new HelloResponse();
     response.setMessage("SKALA에 오신 것을 환영합니다.");
     return response;   
    }
}
