package az.com.etask.service.impl;

import az.com.etask.model.User;
import az.com.etask.repository.inter.UserRepository;
import az.com.etask.service.inter.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    public UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean signUp(User user) {
        try{
            log.info(" Sig up user :{}",user);
            return userRepository.signUp(user);
        }catch (Exception ex){
            log.error(""+ex);
            return false;
        }

    }

    @Override
    public User signIn(String username, String password) {
        try{
            log.info("Sign in username :{}, password : {}",username,password);
            return userRepository.signIn(username, password);
        }catch (Exception ex){
            log.error(""+ex);
            return null;
        }

    }

    @Override
    public boolean addUser(User user) {
        try{
            log.info("Add User :{}",user);
            return userRepository.addUser(user);
        }catch (Exception ex){
            log.error(""+ex);
            return false;
        }

    }

    @Override
    public User getUserById(Long id) {
        try{
            log.info("Get user by id :{}",id);
            return userRepository.getUserById(id);
        }catch (Exception ex){
            log.error(""+ex);
            return null;
        }

    }

    @Override
    public User getUserByUsername(String username) {
        try{
            log.info("Get user by username :{}",username);
            return userRepository.getUserByUsername(username);
        }catch (Exception ex){
            log.error(""+ex);
            return null;
        }

    }
}
