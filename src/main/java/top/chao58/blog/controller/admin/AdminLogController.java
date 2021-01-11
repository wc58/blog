package top.chao58.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.chao58.blog.entity.qo.LogQo;
import top.chao58.blog.entity.vo.ResponseVo;
import top.chao58.blog.entity.vo.admin.LogVo;
import top.chao58.blog.service.LogService;

@RestController
@RequestMapping("/admin/log")
public class AdminLogController {

    @Autowired
    private LogService logService;


    @GetMapping("/getAdminLogReceptionPage")
    public ResponseVo<LogVo> getAdminLogReceptionPage(Integer page, Integer limit, LogQo logQo) {
        ResponseVo<LogVo> responseVo = new ResponseVo<>();
        logService.getAdminLogReceptionPage(page, limit, logQo, responseVo, 1);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/getAdminLogBackstagePage")
    public ResponseVo<LogVo> getAdminLogBackstagePage(Integer page, Integer limit, LogQo logQo) {
        ResponseVo<LogVo> responseVo = new ResponseVo<>();
        logService.getAdminLogReceptionPage(page, limit, logQo, responseVo, 0);
        responseVo.setCode(0);
        return responseVo;
    }

    @GetMapping("/deleteLogById")
    public ResponseVo<LogVo> deleteLogById(Integer id) {
        ResponseVo<LogVo> responseVo = new ResponseVo<>();
        logService.deleteLogById(id);
        responseVo.setCode(0);
        return responseVo;
    }


}
