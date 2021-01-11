package top.chao58.blog.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan("top.chao58")
@MapperScan("top.chao58.blog.dao")
public class BlogConfig {
}
