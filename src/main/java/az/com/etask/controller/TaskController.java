package az.com.etask.controller;

import az.com.etask.model.Company;
import az.com.etask.model.Task;
import az.com.etask.model.TaskToUser;
import az.com.etask.service.inter.TaskService;
import az.com.etask.util.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private TaskService taskService;

    private DateConverter dateConverter;

    @Autowired
    public TaskController(TaskService taskService, DateConverter dateConverter) {
        this.taskService = taskService;
        this.dateConverter = dateConverter;
    }

    @GetMapping("/getTasksByCompanyId")
    public ResponseEntity<?> getTasksByCompanyId(@RequestParam("userId") Long userId) {
        List<TaskToUser> list = taskService.getTasksByCompanyId(userId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/addTaskToUser")
    public ResponseEntity<?> signUp(@RequestParam("creatorId") Long creatorId,
                                    @RequestParam("userId") Long userId,
                                    @RequestParam("taskId") Long taskId) {
        boolean result = taskService.addTaskToUser(creatorId, userId, taskId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/createTask")
    public ResponseEntity<?> createTask(@RequestParam("title") String title,
                                        @RequestParam("description") String description,
                                        @RequestParam("deadline") String deadline,
                                        @RequestParam("companyId") Long companyId) {
        Company company = new Company(companyId, null, null, null, null);
        Task task = new Task(null, title, description, dateConverter.stringToDate(deadline), 1, company);
        boolean result = taskService.createTask(task);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
