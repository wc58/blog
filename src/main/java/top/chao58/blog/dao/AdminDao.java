package top.chao58.blog.dao;

import org.apache.ibatis.annotations.Param;
import top.chao58.blog.entity.po.Admin;

public interface AdminDao {
    Admin getAdminByUsername(@Param("username") String username);

    Integer getAdminSize();
}