package com.jeffy.dundun.cloud.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * @param registry 配置静态资源放行
     *                 这里配置了  就不需要再配置文件配置
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**/**").addResourceLocations("classpath:/static/");
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new LoginHandlerInterceptor());
        String[] exten = new String[]{"/login/**","/static/**/**","/index","/check"};//对登录和静态资源不拦截
        interceptorRegistration.excludePathPatterns(exten);
        interceptorRegistration.addPathPatterns("/**");//拦截所有的请求
        super.addInterceptors(registry);
    }

}
