package com.hzau.feidian.hzauaudiobook.config;

import com.hzau.feidian.hzauaudiobook.interpreter.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 项三六
 * @time 2019/4/17 11:12
 * @comment
 */

@Configuration
public class InterpreterConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;

    public InterpreterConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/wechat/**",
                        "/login",
                        "/validate",
                        "/activity/upload/**",
                        "/bookAudio/upload/**",
                        "/book/upload/**"
                );
    }
}
