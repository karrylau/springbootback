package com.karry.springbootmybatis.controller;

import com.karry.springbootmybatis.pojo.humanRes;
import com.karry.springbootmybatis.service.HumanRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HumanRController {

    @Autowired
    private HumanRService humanRService;

    @GetMapping("/Home")
    public humanRes getAllGeniService() {
        return humanRService.getAll();
    }
}