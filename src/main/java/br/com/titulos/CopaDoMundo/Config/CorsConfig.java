package br.com.titulos.CopaDoMundo.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:5173",
                        "https://copa-do-mundo-x2iq.vercel.app",
                        "https://copa-do-mundo-x2iq-qmqcmq3tk-caios-projects-dc5af49f.vercel.app"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE",  "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }

}
