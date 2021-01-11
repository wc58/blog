package top.chao58.blog.dao;


import org.apache.ibatis.annotations.Param;
import top.chao58.blog.entity.po.Leave;
import top.chao58.blog.entity.qo.CommentQo;
import top.chao58.blog.entity.vo.LeaveVo;
import top.chao58.blog.entity.vo.admin.AdminCommentVo;

import java.util.List;

public interface LeaveDao {
    List<LeaveVo> queryLeave(int pid);

    void insertLeave(Leave leave);

    int getAdminLeaveCommentTotal(@Param("commentQo") CommentQo commentQo);

    List<AdminCommentVo> getAdminLeaveCommentPage(@Param("page") Integer page, @Param("limit") Integer limit, @Param("commentQo") CommentQo commentQo);


    void updateAdminLeaveComment(@Param("id") Integer id, @Param("content") String content);

    void deleteAdminLeaveCommentById(@Param("id") Integer id);

    List<Integer> getAdminLeaveCommentsByPid(@Param("pid") Integer pid);

    Integer getLeaveSize();

}