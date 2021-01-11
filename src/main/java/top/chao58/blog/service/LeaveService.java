package top.chao58.blog.service;


import top.chao58.blog.entity.qo.CommentQo;
import top.chao58.blog.entity.vo.LeaveVo;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.admin.AdminCommentVo;

import java.util.List;

public interface LeaveService {
    List<LeaveVo> getLeave();

    void addLeave(String id, String userId, String content);

    void getAdminLeaveCommentPage(Integer page, Integer limit, CommentQo commentQo, ResponseVo<AdminCommentVo> responseVo);

    void updateAdminLeaveComment(Integer id, String content);

    void deleteAdminLeaveCommentById(Integer id);

    Integer getLeaveSize();
}