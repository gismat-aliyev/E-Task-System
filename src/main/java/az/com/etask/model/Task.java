package az.com.etask.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Task {
    private Long taskId;
    private String title;
    private String description;
    private Date deadline;
    private int status;
    private Company company;
}
