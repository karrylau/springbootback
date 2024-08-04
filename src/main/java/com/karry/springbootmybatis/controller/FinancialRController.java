package com.karry.springbootmybatis.controller;
import com.karry.springbootmybatis.pojo.financialRes;
import com.karry.springbootmybatis.service.FinancialRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/geni2")
public class FinancialRController {

    @Autowired
    private FinancialRService financialRService;

    @GetMapping("/getAllF")
    public List<financialRes> getAllF() {
//        System.out.println("2");
        return financialRService.getAll();
    }
}
