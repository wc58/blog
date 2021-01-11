package top.chao58.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chao58.blog.dao.LogDao;
import top.chao58.blog.entity.po.Log;
import top.chao58.blog.entity.qo.LogQo;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.admin.LogVo;
import top.chao58.blog.service.LogService;

import javax.annotation.Resource;

@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogDao logDao;

    @Override
    public void addLog(Log log) {
        logDao.addLog(log);
    }

    @Override
    public void deleteById(Integer id) {
        logDao.deleteById(id);
    }

    @Override
    public void getAdminLogReceptionPage(Integer page, Integer limit, LogQo logQo, ResponseVo<LogVo> responseVo, int type) {
        responseVo.setCount(logDao.getAdminLogReceptionTotal(logQo, type));
        page = (page - 1) * limit;//计算起始处
        responseVo.setData(logDao.getAdminLogReceptionPage(page, limit, logQo, type));
    }

    @Override
    public void deleteLogById(Integer id) {
        logDao.deleteById(id);
    }

}
