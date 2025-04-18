package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WSDLController {

    @GetMapping("/add")
    public String add(@RequestParam int num1, @RequestParam int num2) {
        int sum = num1 + num2;
        return "Hasil penjumlahan: " + sum;
    }
}
