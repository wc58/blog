package top.chao58.blog.service;

import top.chao58.blog.entity.po.Admin;

public interface AdminService {
    Admin getAdminByUsername(String username);

    Integer getAdminSize();


}