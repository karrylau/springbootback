package com.karry.springbootmybatis.controller;
import com.karry.springbootmybatis.pojo.*;
import com.karry.springbootmybatis.service.GeniService;
import com.karry.springbootmybatis.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class MapController {
    @Autowired
    private MapService MapService;

    @PostMapping("/map")
    public map getmap(@RequestBody String school) {
        return MapService.getmap();
    }
}
