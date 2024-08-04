package com.karry.springbootmybatis.controller;

import com.karry.springbootmybatis.pojo.humanRes;
import com.karry.springbootmybatis.service.HumanRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/geni1")
public class HumanRController {

    @Autowired
    private HumanRService humanRService;

    @GetMapping("/getAllH")
    public List<humanRes> getAllGeniService() {
//        System.out.println("1");
        return humanRService.getAll();
    }
}