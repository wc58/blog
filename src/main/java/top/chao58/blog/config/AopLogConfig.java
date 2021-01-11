package top.chao58.blog.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.chao58.blog.annotation.SystemLog;
import top.chao58.blog.entity.po.Admin;
import top.chao58.blog.entity.po.Log;
import top.chao58.blog.entity.vo.UserVo;
import top.chao58.blog.service.LogService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class AopLogConfig {

    @Autowired
    private LogService logService;

    @Pointcut("execution(* top.chao58.blog.controller..*.*(..))")
    private void logAspect() {
    }


    @Around("logAspect()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        Integer uid = -2;
        String ip = "0.0.0.0";
        int result = 1;
        int type = 1;
        Date startTime = new Date();
        String methodName = "暂无";
        String moduleName = "暂无";
        String client = "暂无";
        Object returnObject = null;
        boolean isSave = false;

        try {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            ip = request.getRemoteHost();
            client = request.getHeader("User-Agent");
            //判断是后台还是前台日志
            String servletPath = request.getServletPath();
            if (servletPath.contains("admin")) {
                type = 0;//后台
                Admin admin = (Admin) request.getSession().getAttribute("admin");
                if (admin != null) {
                    uid = admin.getId();
                }
            } else {
                UserVo user = (UserVo) request.getSession().getAttribute("user");
                if (user != null) {
                    uid = user.getId();
                }
            }
            //目标对象
            Class<?> targetClass = proceedingJoinPoint.getTarget().getClass();
            //获取注解上的信息
            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = methodSignature.getMethod();
            SystemLog moduleAnnotation = targetClass.getAnnotation(SystemLog.class);
            if (moduleAnnotation != null) {
                moduleName = moduleAnnotation.module();
                isSave = moduleAnnotation.isSave();
            }
            SystemLog methodAnnotation = method.getAnnotation(SystemLog.class);
            if (methodAnnotation != null) {
                methodName = methodAnnotation.method();
            }
            //指定目标方法
            returnObject = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            result = 0;
        } finally {
            Date endTime = new Date();
            Log log = new Log();
            log.setUid(uid);
            log.setIp(ip);
            log.setModule(moduleName);
            log.setMethod(methodName);
            log.setClient(client);
            log.setCurrentDate(startTime);
            log.setTotalTime((int) (endTime.getTime() - startTime.getTime()));
            log.setResult(result);
            log.setType(type);
            if (isSave) {
                logService.addLog(log);
            }
        }
        return returnObject;
    }

}
