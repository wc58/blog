package top.chao58.blog.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import top.chao58.blog.properties.parse.PropertiesInterceptor;

/**
 * @author: ChaoSir
 * @date: 2021-02-15 11:00
 **/
@Component
public class PropertiesBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.endsWith("Properties") && !beanName.contains(".")) {
            //把过滤的properties进行代理
            return PropertiesInterceptor.createProxy(bean);
        }
        return bean;
    }
}
