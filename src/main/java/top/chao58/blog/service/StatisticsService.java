package top.chao58.blog.service;

import top.chao58.blog.entity.po.Statistics;

public interface StatisticsService {

    void incrData(Statistics statistics);

    Statistics getToday();

    Statistics getYesterday();

    Statistics getWeek();

    Statistics getMonth();

    Statistics getYear();

}
