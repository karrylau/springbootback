package com.karry.springbootmybatis.controller;


import com.karry.springbootmybatis.service.LeslieService;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LeslieController {
    //最大生育年龄 max
    //3年一段
    //各年龄段生育率、存活率 a,b
//    @Autowired
//    private LeslieService leslieService;
//
//    @GetMapping("/Lesile")
//    public int getAllGeniService() {
//        return leslieService.getnum();
//    }

}
