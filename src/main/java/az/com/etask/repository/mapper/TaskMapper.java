package az.com.etask.repository.mapper;

import az.com.etask.model.Company;
import az.com.etask.model.Task;
import az.com.etask.model.TaskToUser;
import az.com.etask.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TaskMapper {
    public Task getTask(ResultSet rs) throws SQLException {
        Task task = new Task();
        if(rs.next()){
            try{
                task.setTaskId(rs.getLong("task_id"));
                task.setDeadline(rs.getDate("deadline"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                Company company = new Company();
                company.setCompanyId(rs.getLong("company_id"));
                task.setCompany(company);
            }catch (Exception ex){
                log.error(""+ex);
            }

        }
        return task;
    }

    public List<TaskToUser> getTaskList(ResultSet rs) throws SQLException {
        List<TaskToUser> taskToUsers = new ArrayList<>();
        while(rs.next()){
            try{
                User user = new User();
                Task task = new Task();
                TaskToUser taskToUser = new TaskToUser();
                user.setUserId(rs.getLong("user_id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setFullName(rs.getString("full_name"));

                task.setTaskId(rs.getLong("task_id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));
                task.setDeadline(rs.getDate("deadline"));

                taskToUser.setTtuId(rs.getLong("ttu_id"));
                taskToUser.setTask(task);
                taskToUser.setUser(user);
                taskToUsers.add(taskToUser);
            }catch (Exception ex){
                log.error(""+ex);
            }
        }
        return taskToUsers;
    }
}
