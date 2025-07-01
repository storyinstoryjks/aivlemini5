package aivlemini.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * 운영 배포 환경에서 image, pdf와 같은 자원에 접근하기 위한 구성
 * [images]
 * 테스트 환경 : http://localhost:8080/images/generated_image_bookName.png
 * 운영 환경 : http://<EXTERNAL-IP>:<PORT>/images/generated_bookName.png
 */

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 리눅스(우분투) 루트 디렉토리에서부터 해당 폴더를 찾음
        // "/images/**" 요청이 -> "/app/images/" 폴더의 파일로 연결됨
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/app/images/");
    }    
}
