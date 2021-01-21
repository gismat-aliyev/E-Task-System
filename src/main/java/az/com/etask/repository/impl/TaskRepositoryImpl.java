package az.com.etask.repository.impl;

import az.com.etask.model.Task;
import az.com.etask.model.TaskToUser;
import az.com.etask.repository.inter.TaskRepository;
import az.com.etask.repository.mapper.TaskMapper;
import az.com.etask.repository.sql.TaskSQL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Slf4j
public class TaskRepositoryImpl implements TaskRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private TaskMapper taskMapper;

    @Autowired
    public TaskRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                              TaskMapper taskMapper) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.taskMapper = taskMapper;
    }

    @Override
    @Transactional
    public boolean addTaskToUser(Long userId, Long taskId) {
        int result = 0;
        try{
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("userId", userId);
            mapSqlParameterSource.addValue("taskId", taskId);
            result = namedParameterJdbcTemplate.update(TaskSQL.ADD_TASK_TO_USER, mapSqlParameterSource);
        }catch (Exception ex){
            log.error(""+ex);
        }
        return result > 0;
    }

    @Override
    public Task getTaskByTaskId(Long taskId) {
        try{
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("taskId", taskId);
            Task task = namedParameterJdbcTemplate.query(TaskSQL.GET_TASK_BY_ID, mapSqlParameterSource, taskMapper::getTask);
            return task;
        }catch (Exception ex){
            log.error(""+ex);
            return null;
        }

    }

    @Override
    public boolean createTask(Task task) {
        try{
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("title", task.getTitle());
            mapSqlParameterSource.addValue("description", task.getDescription());
            mapSqlParameterSource.addValue("companyId", task.getCompany().getCompanyId());
            mapSqlParameterSource.addValue("deadline", task.getDeadline());
            int isExist = namedParameterJdbcTemplate.update(TaskSQL.CREATE_TASK, mapSqlParameterSource);
            return isExist > 0;
        }catch (Exception ex){
            log.error(""+ex);
            return false;
        }

    }

    @Override
    public List<TaskToUser> getTasksByCompanyId(Long companyId) {
        try{
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("companyId", companyId);
            List<TaskToUser> taskToUsers = namedParameterJdbcTemplate.
                    query(TaskSQL.GET_TASK_LIST_BY_COMPANY_ID,mapSqlParameterSource, taskMapper::getTaskList);
            return taskToUsers;
        }catch (Exception ex){
            log.error(""+ex);
            return null;
        }

    }
}
