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
    private static final UsersRepository usersRepository = new UsersRepository() {
        @Override
        public <S extends UserModel> S save(S s) {
            return null;
        }

        @Override
        public <S extends UserModel> Iterable<S> saveAll(Iterable<S> iterable) {
            return null;
        }

        @Override
        public Optional<UserModel> findById(Long aLong) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
        }

        @Override
        public Iterable<UserModel> findAll() {
            return null;
        }

        @Override
        public Iterable<UserModel> findAllById(Iterable<Long> iterable) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(UserModel userModel) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Long> iterable) {

        }

        @Override
        public void deleteAll(Iterable<? extends UserModel> iterable) {

        }

        @Override
        public void deleteAll() {

        }
    };

    static {
        UserModel defaultAdminUser = new UserModel();
        defaultAdminUser.setUserName(DEFAULT_ADMIN_USER_NAME);
        defaultAdminUser.setEncryptedPassword(DEFAULT_ADMIN_USER_PASSWORD);
        usersRepository.save(defaultAdminUser);
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServiceApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
