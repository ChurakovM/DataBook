package com.example.authenticationservice.services;

import static java.util.Collections.emptyList;

import com.example.authenticationservice.mappers.UserMapper;
import com.example.authenticationservice.models.UserModel;
import com.example.authenticationservice.persistence.UsersRepository;
import com.example.authenticationservice.requests.PostUserRequest;
import com.example.authenticationservice.requests.PostUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;
    private final UsersRepository usersRepository;
    // TODO Why do I have initialize this variable explicitly?
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PostUserResponse createUser(PostUserRequest postUserRequest) {
        PostUserResponse createdEndUser = userMapper.postEndUserRequestToPostUserResponse(postUserRequest);
        UserModel newUserForRepo = userMapper.postUserRequestToUserModel(postUserRequest);
        // TODO start to use hash passwords, learn difference between hash and encrypted passwords
        newUserForRepo.setEncryptedPassword(bCryptPasswordEncoder.encode(postUserRequest.getPassword()));
        usersRepository.save(newUserForRepo);
        return createdEndUser;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserModel userModel = usersRepository.findByUserName(userName);
        if(userModel == null) {
            throw new UsernameNotFoundException(userName);
        } else {
            return new User(userModel.getUserName(), userModel.getEncryptedPassword(),
                true, true, true, true, emptyList());
        }
    }
}
