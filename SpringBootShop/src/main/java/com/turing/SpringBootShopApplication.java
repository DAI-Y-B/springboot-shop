package com.turing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Configuration
//@MapperScan("com.turing.mapper")
public class SpringBootShopApplication implements WebMvcConfigurer {

    // 编写拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //对于登录拦截的设置
//        InterceptorRegistration registration1 = registry.addInterceptor(new LoginInterceptor());
//        //拦截请求
//        registration1.addPathPatterns("/**");
//        //指定不拦截的请求(登录不拦截)
//        //registration1.excludePathPatterns("/user/login");
//        //对于其他拦截的设置
//        InterceptorRegistration registration2 = registry.addInterceptor(new OtherInterceptor());
//        //拦截请求
//        registration2.addPathPatterns("/**");
//
//    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootShopApplication.class, args);
    }

}
