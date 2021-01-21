package az.com.etask.repository.inter;

import az.com.etask.model.Task;
import az.com.etask.model.TaskToUser;

import java.util.List;

public interface TaskRepository {
    boolean addTaskToUser(Long userId,Long taskId);

    Task getTaskByTaskId(Long taskId);

    boolean createTask(Task task);

    List<TaskToUser> getTasksByCompanyId(Long companyId);


}
