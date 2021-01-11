package top.chao58.blog.service.impl;

import org.springframework.stereotype.Service;
import top.chao58.blog.dao.AdminDao;
import top.chao58.blog.entity.po.Admin;
import top.chao58.blog.service.AdminService;

import javax.annotation.Resource;
import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;

    @Override
    public Admin getAdminByUsername(String username) {
        return adminDao.getAdminByUsername(username);
    }

    @Override
    public Integer getAdminSize() {
        return adminDao.getAdminSize();
    }
}