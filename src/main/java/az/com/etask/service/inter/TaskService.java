package az.com.etask.service.inter;

import az.com.etask.model.Task;
import az.com.etask.model.TaskToUser;

import java.util.List;

public interface TaskService {
    boolean addTaskToUser(Long creatorId, Long userId, Long taskId);

    boolean createTask(Task task);

    List<TaskToUser> getTasksByCompanyId(Long userId);

    Task getTaskByTaskId(Long taskId);
}
