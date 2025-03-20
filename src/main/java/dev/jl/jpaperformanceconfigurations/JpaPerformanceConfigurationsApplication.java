package dev.jl.jpaperformanceconfigurations;

import dev.jl.jpaperformanceconfigurations.post.PostService;
import dev.jl.jpaperformanceconfigurations.user.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class JpaPerformanceConfigurationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaPerformanceConfigurationsApplication.class, args);
    }

    @Bean
    ApplicationRunner init(PostService postService, UserService userService){
        return args -> {
            userService.importPosts();
            postService.importPosts();
        };
    }

}
