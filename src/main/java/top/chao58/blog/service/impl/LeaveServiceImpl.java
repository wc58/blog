package top.chao58.blog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.chao58.blog.dao.LeaveDao;
import top.chao58.blog.entity.po.Leave;
import top.chao58.blog.entity.qo.CommentQo;
import top.chao58.blog.entity.vo.LeaveVo;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.admin.AdminCommentVo;
import top.chao58.blog.service.LeaveService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (Leave)表服务实现类
 *
 * @author makejava
 * @since 2020-12-06 21:35:50
 */
@Service("leaveService")
public class LeaveServiceImpl implements LeaveService {
    @Resource
    private LeaveDao leaveDao;

    @Override
    public List<LeaveVo> getLeave() {
        List<LeaveVo> parentLeaves = leaveDao.queryLeave(0);
        for (LeaveVo parentLeaf : parentLeaves) {
            //递归的查出子评论
            childrenLeave(parentLeaf.getId(), parentLeaf.getName(), parentLeaf.getChildrenLeaves());
        }
        return parentLeaves;
    }

    @Override
    public void addLeave(String uid, String pid, String content) {
        Leave leave = new Leave();
        //为空说明为顶级评论
        pid = StringUtils.isEmpty(pid) ? "0" : pid;
        leave.setPid(Integer.parseInt(pid));
        leave.setUid(Integer.parseInt(uid));
        leave.setContent(content);
        leave.setHidden(0);
        leave.setCreateTime(new Date());
        leaveDao.insertLeave(leave);
    }


    /*=============start=====================admin======================================================================================*/
    @Override
    public void getAdminLeaveCommentPage(Integer page, Integer limit, CommentQo commentQo, ResponseVo<AdminCommentVo> responseVo) {
        responseVo.setCount(leaveDao.getAdminLeaveCommentTotal(commentQo));
        page = (page - 1) * limit;//计算起始处
        responseVo.setData(leaveDao.getAdminLeaveCommentPage(page, limit, commentQo));
    }

    @Override
    public void updateAdminLeaveComment(Integer id, String content) {
        leaveDao.updateAdminLeaveComment(id, content);
    }

    @Override
    public void deleteAdminLeaveCommentById(Integer id) {
        deleteChildrenLeaveComment(id);
    }

    @Override
    public Integer getLeaveSize() {
        return leaveDao.getLeaveSize();
    }

    /**
     * 递归删除子评论
     */
    private void deleteChildrenLeaveComment(Integer id) {
        //首先先删除这个评论
        leaveDao.deleteAdminLeaveCommentById(id);
        //然后以该评论的id作为父id，然后再递归的删除
        List<Integer> ids = leaveDao.getAdminLeaveCommentsByPid(id);
        if (ids != null && ids.size() != 0) {
            ids.forEach(this::deleteChildrenLeaveComment);
        }
    }

    /*=============end=====================admin======================================================================================*/


    private void childrenLeave(Integer pid, String name, List<LeaveVo> parentLeaveVo) {
        List<LeaveVo> leaveVos = leaveDao.queryLeave(pid);
        for (LeaveVo leaveVo : leaveVos) {
            leaveVo.setParentName(name);
            parentLeaveVo.add(leaveVo);
            childrenLeave(leaveVo.getId(), leaveVo.getName(), parentLeaveVo);
        }
    }
}