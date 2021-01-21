package az.com.etask.repository.inter;

import az.com.etask.model.User;

public interface UserRepository {
    boolean signUp(User user);
    User signIn(String username,String password);
    boolean addUser(User user);
    User getUserById(Long id);
    boolean controlUserIdAndCompanyId(Long userId, Long companyId);
    User getUserByUsername(String username);
}
