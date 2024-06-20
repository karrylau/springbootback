package com.karry.springbootmybatis.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.karry.springbootmybatis.common.R;
import com.karry.springbootmybatis.pojo.Admin;
import jakarta.servlet.http.HttpSession;
import com.karry.springbootmybatis.model.request.AdminRequest;
import org.springframework.stereotype.Service;

@Service
public interface AdminService extends IService<Admin> {
    R verityPasswd(AdminRequest adminRequest, HttpSession session);

}
