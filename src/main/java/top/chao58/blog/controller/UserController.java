package top.chao58.blog.controller;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.chao58.blog.annotation.SystemLog;
import top.chao58.blog.entity.po.Statistics;
import top.chao58.blog.entity.po.User;
import top.chao58.blog.entity.vo.UserVo;
import top.chao58.blog.service.StatisticsService;
import top.chao58.blog.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/user")
@SystemLog(module = "用户模块")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/qqLogin")
    @SystemLog(method = "用户登录")
    public String qqLogin(String currentUrl, HttpServletRequest request, HttpSession session) {
        String qqLoginUrl = null;
        try {
            session.setAttribute("currentUrl", currentUrl);
            //请求QQ登录页面
            qqLoginUrl = new Oauth().getAuthorizeURL(request);
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
        return "redirect:" + qqLoginUrl;
    }

    @GetMapping("/logout")
    @ResponseBody
    @SystemLog(method = "用户退出")
    public Integer logout(HttpSession session) {
        session.invalidate();
        return 200;
    }


    @RequestMapping("/qqLoginCallback")
    @SystemLog(method = "QQ回调")
    public String connection(HttpServletRequest request, HttpSession session) {
        //如果登录状态则直接返回
        if (session.getAttribute("user") != null) {
            return "redirect:" + session.getAttribute("currentUrl");
        }
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            String accessToken;
            String thirdId;
            long tokenExpireIn;
            if (accessTokenObj.getAccessToken().equals("")) {
                System.out.print("没有获取到响应参数");
            } else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();
                request.getSession().setAttribute("demo_access_token", accessToken);
                request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));
                OpenID openIDObj = new OpenID(accessToken);
                thirdId = openIDObj.getUserOpenID();
                UserInfo qzoneUserInfo = new UserInfo(accessToken, thirdId);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                if (userInfoBean.getRet() == 0) {
                    //QQ返回的用户昵称
                    String nickname = userInfoBean.getNickname();
                    //头像
                    String icon = userInfoBean.getAvatar().getAvatarURL50();
                    //封装用户信息
                    packageUserInfo(thirdId, nickname, icon, session);
                }
            }
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
        return "redirect:" + session.getAttribute("currentUrl");
    }


    private void packageUserInfo(String thirdId, String nickname, String icon, HttpSession session) {
        //首先根据tid搜索
        UserVo userVo = userService.getByThirdId(thirdId);
        User user = new User();
        user.setName(nickname);
        user.setIcon(icon);
        user.setTid(thirdId);
        Date date = new Date();
        user.setModifyTime(date);
        //第一次登陆
        if (userVo == null) {
            user.setCreateTime(date);
            userService.addUser(user);
            //统计
            statisticsService.incrData(new Statistics().setUser(1));
            userVo = userService.getByThirdId(thirdId);
        } else if (!thirdId.equals("8E1544B0D015EC98612B39DD5D5B90B0")) {
            userService.updateModifyTime(user);
        }
        session.setAttribute("user", userVo);
        session.setAttribute("userId", userVo.getId());
    }


}
