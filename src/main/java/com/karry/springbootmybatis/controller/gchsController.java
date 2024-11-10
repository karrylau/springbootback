package com.karry.springbootmybatis.controller;

import com.karry.springbootmybatis.pojo.gchs;
import com.karry.springbootmybatis.pojo.result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.karry.springbootmybatis.service.gchsService;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/gchs")
public class gchsController {
    @Autowired
    private gchsService gchsService;
    @GetMapping
    public result listfcec(){
        log.info("查询全部部门数据");
        //调用service查询部门数据
        List<gchs> deptList =  gchsService.listgchs();
        return result.success(deptList);
    }

}
