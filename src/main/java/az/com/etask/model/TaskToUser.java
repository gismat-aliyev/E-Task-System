package az.com.etask.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaskToUser {
    private Long ttuId;
    private Task task;
    private User user;
}
