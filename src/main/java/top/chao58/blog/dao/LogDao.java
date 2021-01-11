package top.chao58.blog.dao;

import org.apache.ibatis.annotations.Param;
import top.chao58.blog.entity.po.Log;
import top.chao58.blog.entity.qo.LogQo;
import top.chao58.blog.entity.vo.admin.LogVo;

import java.util.List;

public interface LogDao {
    void addLog(@Param("log") Log log);

    void deleteById(@Param("id") Integer id);

    List<Log> getLogByType(@Param("type") Integer type);

    int getAdminLogReceptionTotal(@Param("logQo") LogQo logQo, @Param("type") int type);

    List<LogVo> getAdminLogReceptionPage(@Param("page") Integer page, @Param("limit") Integer limit, @Param("logQo") LogQo logQo, @Param("type") int type);
}
