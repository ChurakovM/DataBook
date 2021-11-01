package com.example.authenticationservice;

import com.example.authenticationservice.models.UserModel;
import com.example.authenticationservice.persistence.UsersRepository;
import java.util.Optional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthenticationServiceApplication {

    // TODO how to create a default admin once the service started?
    private static final String DEFAULT_ADMIN_USER_NAME = "admin";
    private static final String DEFAULT_ADMIN_USER_PASSWORD = "admin";
    //private final UsersRepository usersRepository;

//    static {
//        UserModel defaultAdminUser = new UserModel();
//        defaultAdminUser.setUserName(DEFAULT_ADMIN_USER_NAME);
//        defaultAdminUser.setEncryptedPassword(DEFAULT_ADMIN_USER_PASSWORD);
//        usersRepository.save(defaultAdminUser);
//    }

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServiceApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
