package top.chao58.blog.dao;


import org.apache.ibatis.annotations.Param;
import top.chao58.blog.entity.dto.DiaryDto;
import top.chao58.blog.entity.po.Diary;
import top.chao58.blog.entity.qo.BaseQo;
import top.chao58.blog.entity.vo.DiaryVo;
import top.chao58.blog.entity.vo.admin.AdminDiaryVo;

import java.util.Date;
import java.util.List;

public interface DiaryDao {
    List<DiaryVo> getDiaries(@Param("statTime") String startTime, @Param("endTime") String endTime);

    int getAdminDiaryTotal(@Param("baseQo") BaseQo baseQo, @Param("recycle") int recycle);

    List<AdminDiaryVo> getAdminDiaryPage(@Param("page") Integer page, @Param("limit") Integer limit, @Param("baseQo") BaseQo baseQo, @Param("recycle") int recycle);


    void updateHiddenById(@Param("id") Integer id, @Param("hiddenValue") int hiddenValue);


    void recycleDiary(@Param("id") Integer id, @Param("deleteTime") Date deleteTime);

    void restoreDiary(@Param("id") Integer id);

    void updateDataById(@Param("diaryDto") DiaryDto diaryDto);

    int deleteDiaryById(@Param("id") Integer id);

    void addDiary(@Param("diary") Diary diary);

    void updateDiary(@Param("id") Integer id, @Param("content") String content, @Param("contentImg") String contentImg);

    List<AdminDiaryVo> getAdminDiaryById(@Param("id") Integer id);

    Integer getDiarySize();

}