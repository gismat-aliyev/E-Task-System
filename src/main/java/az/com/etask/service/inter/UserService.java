package az.com.etask.service.inter;

import az.com.etask.model.User;

public interface UserService {
    boolean signUp(User user);
    User signIn(String username,String password);
    boolean addUser(User user);
    User getUserById(Long id);
    User getUserByUsername(String username);
}
