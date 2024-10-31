package com.karry.springbootmybatis.controller;

import com.karry.springbootmybatis.pojo.*;
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
    @PostMapping("/genicalculate")
    public Geniresult getGeniselect(@RequestBody Genicalculate genicalculate) {
        return geniService.GeniCalculate(genicalculate);
    }
}