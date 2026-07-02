package com.buu.oa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 文件上传静态资源映射配置
 * 将本地上传目录映射为可访问的URL路径，用于发票、公告富文本图片展示
 */
@Configuration
public class FileUploadConfig implements WebMvcConfigurer {

    /**
     * 文件上传根路径，从配置文件读取，默认值为本地路径
     */
    @Value("${file.upload.path:D:/IDEA/oa/upload/}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 通用上传文件映射（报销发票等）
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + uploadPath);

        // 公告富文本图片映射
        registry.addResourceHandler("/upload/notice/**")
                .addResourceLocations("file:" + uploadPath + "notice/");
    }
}