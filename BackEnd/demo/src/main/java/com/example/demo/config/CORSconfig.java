package com.example.demo.config; // Adjust your package name

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Marks this class as a source of bean definitions
public class CORSconfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // This means apply CORS to all endpoints in your API
                .allowedOrigins("http://localhost:5173") // Allow requests from your React app's origin
                // If your React app could be deployed to multiple origins, you can add more:
                // .allowedOrigins("http://localhost:5173", "https://yourproductionapp.com")
                // Or for development, temporarily use "*" (NOT recommended for production):
                // .allowedOrigins("*") // Allows all origins - BE CAREFUL WITH THIS IN PRODUCTION!

                .allowedMethods("*") // Specify allowed HTTP methods
                .allowedHeaders("*") // Allow all headers (you can specify specific headers like "Content-Type", "Authorization")
                .allowCredentials(true) // Allow cookies, authorization headers, etc.
                .maxAge(3600); // How long the pre-flight request results can be cached (in seconds)
    }
}
