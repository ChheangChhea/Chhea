package co.istad.cms.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Category {
    private Integer id;
    private String name;
    private Boolean isDeleted;
}
