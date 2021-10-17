package com.example.authenticationservice.persistence;

import com.example.authenticationservice.models.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserModel, Long> {

}
