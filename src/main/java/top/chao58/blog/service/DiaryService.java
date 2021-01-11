package top.chao58.blog.service;


import top.chao58.blog.entity.dto.DiaryDto;
import top.chao58.blog.entity.qo.BaseQo;
import top.chao58.blog.entity.vo.DiaryVo;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.admin.AdminArticleVo;
import top.chao58.blog.entity.vo.admin.AdminDiaryVo;

import java.util.HashMap;
import java.util.List;

public interface DiaryService {
    HashMap<String, List<DiaryVo>> getDiaries(String startTime, String endTime);

    void getAdminDiaryPage(ResponseVo<AdminDiaryVo> responseVo, Integer page, Integer limit, BaseQo baseQo, int i);

    void updateHiddenById(Integer id, Boolean isHidden);

    void recycleDiary(Integer id);

    void restoreDiary(Integer id);

    void updateDataById(DiaryDto articleDto);

    void deleteDiaryById(ResponseVo<AdminArticleVo> responseVo, Integer id);

    Integer addDiary(DiaryDto content);

    void updateDiary(DiaryDto content);


    List<AdminDiaryVo> getAdminDiaryById(Integer id);

    Integer getDiarySize();
}