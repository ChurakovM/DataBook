package com.example.authenticationservice.persistence;

import com.example.authenticationservice.models.EndUserModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EndUserPersistenceService implements EndUsersRepository{

//    // TODO temporarily solution
//    private final List<EndUserModel> endUsers = new ArrayList<>();
//
//    public void addEndUser(EndUserModel endUserModel) {
//        endUsers.add(endUserModel);
//    }

    @Override
    public <S extends EndUserModel> S save(S s) {
        return null;
    }

    @Override
    public <S extends EndUserModel> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<EndUserModel> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<EndUserModel> findAll() {
        return null;
    }

    @Override
    public Iterable<EndUserModel> findAllById(Iterable<Long> iterable) {
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
    public void delete(EndUserModel endUserModel) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends EndUserModel> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
