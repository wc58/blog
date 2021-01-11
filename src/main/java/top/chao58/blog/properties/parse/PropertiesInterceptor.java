package top.chao58.blog.properties.parse;

import lombok.extern.java.Log;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import top.chao58.blog.util.StringUtil;

import java.lang.reflect.Method;

/**
 * 主要产生代理对象，表面对对象操作，实则底层操作的配置文件
 */
@Log
public class PropertiesInterceptor implements MethodInterceptor {

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        String simpleName = o.getClass().getSuperclass().getSimpleName().replace("Properties", "").toLowerCase();
        PropertiesUtil propertiesUtil = new PropertiesUtil(simpleName);
        //获取属性
        if (method.getName().startsWith("get")) {
            String key = StringUtil.toLowerCase(method.getName().replace("get", ""));
            log.info(simpleName + "类获取该属性：" + key);
            return propertiesUtil.get(key);
        }
        //修改属性
        if (method.getName().startsWith("set")) {
            String key = StringUtil.toLowerCase(method.getName().replace("set", ""));
            log.info(simpleName + "类修改该属性：" + key);
            //获取第一个参数
            propertiesUtil.set(key, objects[0] + "");
        }
        return methodProxy.invokeSuper(o, objects);
    }

    public static Object createProxy(Class<?> clazz) {
        log.info("创建代理对象：" + clazz);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new PropertiesInterceptor());
        return enhancer.create();
    }

}
