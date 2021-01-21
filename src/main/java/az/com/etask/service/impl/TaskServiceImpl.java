package az.com.etask.service.impl;

import az.com.etask.model.Task;
import az.com.etask.model.TaskToUser;
import az.com.etask.model.User;
import az.com.etask.repository.inter.TaskRepository;
import az.com.etask.service.inter.TaskService;
import az.com.etask.service.inter.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    public TaskRepository taskRepository;

    public UserService userService;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public boolean addTaskToUser(Long creatorId, Long userId, Long taskId) {
        try {
            log.info("add task creator id : {}  user id : {}  task id : {}", creatorId, userId, taskId);
            User userCreator = userService.getUserById(creatorId);
            User userAdd = userService.getUserById(userId);
            boolean result1 = (userCreator.getCompany().getCompanyId().equals(userAdd.getCompany().getCompanyId()));
            Task task = getTaskByTaskId(taskId);
            boolean result2 = (userCreator.getCompany().getCompanyId().equals(task.getCompany().getCompanyId()));

            if (result1 && result2) {
                return taskRepository.addTaskToUser(userId, taskId);
            } else {
                return false;
            }
        } catch (Exception ex) {
            log.error("" + ex);
            return false;
        }

    }

    @Override
    public boolean createTask(Task task) {
        try {
            log.info("create task :{}", task);
            return taskRepository.createTask(task);
        } catch (Exception ex) {
            log.error("" + ex);
            return false;
        }

    }

    @Override
    public List<TaskToUser> getTasksByCompanyId(Long userId) {
        try {
            log.info("Get task by company. user id :{}", userId);
            List<TaskToUser> taskToUsers;
            User user = userService.getUserById(userId);
            taskToUsers = taskRepository.getTasksByCompanyId(user.getCompany().getCompanyId());
            return taskToUsers;
        } catch (Exception ex) {
            log.error("" + ex);
            return null;
        }

    }

    @Override
    public Task getTaskByTaskId(Long taskId) {
        try {
            log.info("get task by id :{}", taskId);
            return taskRepository.getTaskByTaskId(taskId);
        } catch (Exception ex) {
            log.error("" + ex);
            return null;
        }

    }
}
