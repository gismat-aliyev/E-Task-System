package az.com.etask.repository.mapper;

import az.com.etask.model.Company;
import az.com.etask.model.Role;
import az.com.etask.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Slf4j
public class UserMapper {


    public User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        if(rs.next()){
            try{
                user.setUserId(rs.getLong("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("full_name"));
                Company company = new Company();
                company.setCompanyId(rs.getLong("company_id"));
                user.setCompany(company);
                Role role = new Role();
                role.setRoleId(rs.getLong("role_id"));
                role.setRoleName(rs.getString("role_name"));
                user.setRole(role);
            }catch (Exception ex){
                log.error(""+ex);
            }
        }
        return user;
    }
}
