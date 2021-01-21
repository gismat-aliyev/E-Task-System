package az.com.etask.repository.impl;

import az.com.etask.model.User;
import az.com.etask.repository.inter.UserRepository;
import az.com.etask.repository.mapper.UserMapper;
import az.com.etask.repository.sql.TaskSQL;
import az.com.etask.util.MD5Generator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private UserMapper userMapper;

    private MD5Generator md5Generator;

    @Autowired
    public UserRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                              UserMapper userMapper,
                              MD5Generator md5Generator) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.userMapper = userMapper;
        this.md5Generator = md5Generator;
    }

    @Override
    public boolean signUp(User user) {
        int result = 0;
        try{
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("companyId", user.getCompany().getCompanyId());
            mapSqlParameterSource.addValue("email", user.getEmail());
            mapSqlParameterSource.addValue("username", user.getUsername());
            mapSqlParameterSource.addValue("password", md5Generator.generateMd5(user.getPassword()));
            mapSqlParameterSource.addValue("fullName", user.getFullName());
            result = namedParameterJdbcTemplate.update(TaskSQL.ADD_ADMIN, mapSqlParameterSource);
        }catch (Exception ex){
            log.error(""+ex);
        }
        return result > 0;
    }

    @Override
    public User signIn(String username, String password) {
        try{
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("username", username);
            mapSqlParameterSource.addValue("password", md5Generator.generateMd5(password));
            User user = namedParameterJdbcTemplate.
                    query(TaskSQL.GET_USER_BY_LOGIN_AND_PASSWORD, mapSqlParameterSource, userMapper::getUser);
            return user;
        }catch (Exception ex){
            log.error(""+ex);
            return null;
        }

    }

    @Override
    public boolean addUser(User user) {
        try{
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("companyId", user.getCompany().getCompanyId());
            mapSqlParameterSource.addValue("email", user.getEmail());
            mapSqlParameterSource.addValue("username", user.getUsername());
            mapSqlParameterSource.addValue("password", md5Generator.generateMd5(user.getPassword()));
            mapSqlParameterSource.addValue("fullName", user.getFullName());
            int isExist = namedParameterJdbcTemplate.update(TaskSQL.ADD_USER, mapSqlParameterSource);
            return isExist > 0;
        }catch (Exception ex){
            log.error(""+ex);
            return false;
        }

    }

    @Override
    public User getUserById(Long id) {
        try{
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("userId", id);
            User user = namedParameterJdbcTemplate.
                    query(TaskSQL.GET_USER_BY_ID, mapSqlParameterSource, userMapper::getUser);
            return user;
        }catch (Exception ex){
            log.error(""+ex);
            return null;
        }
    }

    @Override
    public boolean controlUserIdAndCompanyId(Long userId, Long companyId) {
        try{
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("userId", userId);
            mapSqlParameterSource.addValue("companyId", companyId);
            User user = namedParameterJdbcTemplate.
                    query(TaskSQL.GET_USER_BY_ID_AND_COMPANY_ID, mapSqlParameterSource, userMapper::getUser);
            return user != null;
        }catch (Exception ex){
            log.error(""+ex);
            return false;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        try{
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("username", username);
            User user = namedParameterJdbcTemplate.
                    query(TaskSQL.GET_USER_BY_USERNAME, mapSqlParameterSource, userMapper::getUser);
            return user;
        }catch (Exception ex){
            log.error(""+ex);
            return null;
        }
    }
}
