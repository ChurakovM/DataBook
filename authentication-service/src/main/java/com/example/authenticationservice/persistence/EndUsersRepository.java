package com.example.authenticationservice.persistence;

import com.example.authenticationservice.models.EndUserModel;
import org.springframework.data.repository.CrudRepository;

public interface EndUsersRepository extends CrudRepository<EndUserModel, Long> {

}
