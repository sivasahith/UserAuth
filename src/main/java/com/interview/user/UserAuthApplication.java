package com.interview.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import com.interview.user.auth.config.WebConfig;

@SpringBootApplication
@Import(value = WebConfig.class)
public class UserAuthApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthApplication.class, args);
	}
}
