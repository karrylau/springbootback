package com.karry.springbootmybatis.controller;

import com.karry.springbootmybatis.pojo.Geniresult;
import com.karry.springbootmybatis.pojo.Geniselect;
import com.karry.springbootmybatis.pojo.peopleselect;
import com.karry.springbootmybatis.pojo.timeselect;
import com.karry.springbootmybatis.service.GeniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GeniController {

    @Autowired
    private GeniService geniService;

    @PostMapping("/geniselect")
    public Geniresult getGeniselect(@RequestBody Geniselect geniselect) {
        return geniService.getGeni(geniselect);
    }
}