package udem.edu.co.cda.configuration;

import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class Config implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry cors){
        cors.addMapping("/**").allowedOrigins("http://localhost:3000").allowedMethods("GET","POST","PUT","DELETE");
    }
}