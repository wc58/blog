package top.chao58.blog.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.chao58.blog.dao.StatisticsDao;
import top.chao58.blog.entity.po.Statistics;
import top.chao58.blog.service.StatisticsService;

import javax.annotation.Resource;


@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Resource
    private StatisticsDao statisticsDao;


    private String getDate() {
        return DateUtil.date().toString("yyyy-MM-dd");
    }

    private void initData() {
        Statistics data = statisticsDao.getStatisticsByDate(getDate());
        if (data == null) {
            statisticsDao.initData(getDate());
        }
    }

    @Override
    public void incrData(Statistics statistics) {
        //初始化今天的记录
        initData();
        statistics.setDate(getDate());
        statisticsDao.incrData(statistics);
    }

    @Override
    public Statistics getToday() {
        initData();
        return statisticsDao.getStatisticsByDate(getDate());
    }

    @Override
    public Statistics getYesterday() {
        String yesterday = DateUtil.yesterday().toString("yyyy-MM-dd");
        return statisticsDao.getStatisticsByDate(yesterday);
    }

    @Override
    public Statistics getWeek() {
        String start = DateUtil.offsetDay(DateUtil.date(), -7).toString("yyyy-MM-dd");
        return statisticsDao.getStatisticsBetweenDate(start, getDate());
    }

    @Override
    public Statistics getMonth() {
        String start = DateUtil.offsetDay(DateUtil.date(), -30).toString("yyyy-MM-dd");
        return statisticsDao.getStatisticsBetweenDate(start, getDate());
    }

    @Override
    public Statistics getYear() {
        String start = DateUtil.offsetDay(DateUtil.date(), -360).toString("yyyy-MM-dd");
        return statisticsDao.getStatisticsBetweenDate(start, getDate());
    }
}
