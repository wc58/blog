package top.chao58.blog.properties.parse;

import lombok.extern.java.Log;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 主要负责找到配置的对象
 */
@Component
@Log
public class PropertiesFactory {


    private Map<Class<?>, Object> properties = new HashMap<>();
    private String basePackage = "top.chao58.blog.properties";

    public PropertiesFactory(String basePackage) {
        this.basePackage = basePackage;
        initProperties();
    }

    public PropertiesFactory() {
        initProperties();
    }

    /**
     * 获取对应的properties对象
     */
    public Object getByClass(Class<?> clazz) {
        return properties.get(clazz);
    }

    /**
     * 初始化Properties集合
     */
    public void initProperties() {
        log.info("初始化Properties工厂");
        log.info("查找" + basePackage + "目录下的文件");
        ArrayList<Class<?>> classes = findClass();
        classes.forEach(clazz -> {
            //创建代理对象
            Object proxy = PropertiesInterceptor.createProxy(clazz);
            properties.put(clazz, proxy);
        });
    }

    private ArrayList<Class<?>> findClass() {
        ArrayList<Class<?>> classes = new ArrayList<>();
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:/" + basePackage.replace(".", "/") + "/*.class");
            for (Resource resource : resources) {
                String fileUri = resource.getURI().toString();
                String fileName = fileUri.substring(fileUri.lastIndexOf('/') + 1, fileUri.lastIndexOf('.'));
                String clazz = basePackage + "." + fileName;
                log.info("扫描到配置文件：" + clazz);
                classes.add(Class.forName(clazz));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

}
