package top.chao58.blog.service;

import top.chao58.blog.entity.po.Log;
import top.chao58.blog.entity.qo.LogQo;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.admin.LogVo;

public interface LogService {

    void addLog(Log log);

    void deleteById(Integer id);


    void getAdminLogReceptionPage(Integer page, Integer limit, LogQo logQo, ResponseVo<LogVo> responseVo, int i);

    void deleteLogById(Integer id);

}
