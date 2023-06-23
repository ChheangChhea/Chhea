package co.istad.cms.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private Integer id;
    private String uuid;
    private String username;
    private String password;
    private String displayName;
    private Boolean isDeleted;
    private LocalDate createdAt;
    private List<Role> roles;
}
