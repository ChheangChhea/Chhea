package co.istad.cms.model;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Content {
    private Integer id;
    private String uuid;
    private String slug;
    private String title;
    private String description;
    private String thumbnail;
    private String keyword;
    private String editor;
    private Boolean isDeleted;
    private LocalDateTime createdAt;
    private Category category;
}
