package top.chao58.blog.service.impl;

import org.springframework.stereotype.Service;
import top.chao58.blog.dao.DiaryDao;
import top.chao58.blog.entity.dto.DiaryDto;
import top.chao58.blog.entity.po.Diary;
import top.chao58.blog.entity.qo.BaseQo;
import top.chao58.blog.entity.vo.DiaryVo;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.admin.AdminArticleVo;
import top.chao58.blog.entity.vo.admin.AdminDiaryVo;
import top.chao58.blog.service.DiaryService;
import top.chao58.blog.util.CommonUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Service("diaryService")
public class DiaryServiceImpl implements DiaryService {
    @Resource
    private DiaryDao diaryDao;

    @Override
    public HashMap<String, List<DiaryVo>> getDiaries(String startTime, String endTime) {
        HashMap<String, List<DiaryVo>> map = new HashMap<>();
        List<DiaryVo> diaries = diaryDao.getDiaries(startTime, endTime);
        for (DiaryVo diary : diaries) {
            //查看日记对应的年
            String year = diary.getCreateTime().toString();
            year = year.substring(year.length() - 4);
            //如果该年份对应的集合为空则创建一个，否则直接返回
            List<DiaryVo> diaryVoList = map.computeIfAbsent(year, k -> new ArrayList<>());
            diaryVoList.add(diary);
        }
        return map;
    }



    /*=============start=====================admin======================================================================================*/

    @Override
    public void getAdminDiaryPage(ResponseVo<AdminDiaryVo> responseVo, Integer page, Integer limit, BaseQo baseQo, int recycle) {
        responseVo.setCount(diaryDao.getAdminDiaryTotal(baseQo, recycle));
        page = (page - 1) * limit;//计算起始处
        responseVo.setData(diaryDao.getAdminDiaryPage(page, limit, baseQo, recycle));
    }

    @Override
    public void updateHiddenById(Integer id, Boolean isHidden) {
        int hiddenValue = isHidden ? 0 : 1;
        diaryDao.updateHiddenById(id, hiddenValue);
    }

    @Override
    public void recycleDiary(Integer id) {
        diaryDao.recycleDiary(id, new Date());
    }

    @Override
    public void restoreDiary(Integer id) {
        diaryDao.restoreDiary(id);
    }

    @Override
    public void updateDataById(DiaryDto diaryDto) {
        diaryDao.updateDataById(diaryDto);
    }

    @Override
    public void deleteDiaryById(ResponseVo<AdminArticleVo> responseVo, Integer id) {
        int count = diaryDao.deleteDiaryById(id);
        if (count == 1) {
            responseVo.setCode(0);
        } else {
            responseVo.setCode(1);
        }
    }

    @Override
    public Integer addDiary(DiaryDto content) {
        Diary diary = new Diary();
        diary.setContent(content.getContent());
        diary.setContentImg(CommonUtils.analyseHtmlByImg(content.getContent()));
        diary.setHidden(0);
        diary.setDel(0);
        diary.setCreateTime(new Date());
        diaryDao.addDiary(diary);
        return diary.getId();
    }

    @Override
    public void updateDiary(DiaryDto content) {
        diaryDao.updateDiary(content.getId(), content.getContent(), CommonUtils.analyseHtmlByImg(content.getContent()));
    }

    @Override
    public List<AdminDiaryVo> getAdminDiaryById(Integer id) {
        return diaryDao.getAdminDiaryById(id);
    }

    @Override
    public Integer getDiarySize() {
        return diaryDao.getDiarySize();
    }
    /*=============end=====================admin======================================================================================*/
}