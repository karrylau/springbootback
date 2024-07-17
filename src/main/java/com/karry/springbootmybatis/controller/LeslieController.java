package com.karry.springbootmybatis.controller;



import com.karry.springbootmybatis.service.LeslieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeslieController {

    @Autowired
    private LeslieService service;

    @GetMapping("/leslie")
    public double[] getLeslieResult() {
        return service.applyLeslieAlgorithm();
    }
}

