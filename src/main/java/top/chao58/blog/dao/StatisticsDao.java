package top.chao58.blog.dao;

import org.apache.ibatis.annotations.Param;
import top.chao58.blog.entity.po.Statistics;

public interface StatisticsDao {

    Statistics getStatisticsByDate(String date);

    Statistics getStatisticsBetweenDate(@Param("start") String star, @Param("end") String end);

    void initData(@Param("date") String date);

    void incrData(@Param("statistics") Statistics statistics);
}
