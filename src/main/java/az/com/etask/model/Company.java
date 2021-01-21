package az.com.etask.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Company {
    private Long companyId;
    private String companyName;
    private String companyPhone;
    private String companyAddress;
    private String companyEmail;
}
