package study.assignment.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateDto {
    @NotBlank(message = "Title cannot be empty") // NOT NULL
    @Size(max = 50, message = "Title cannot exceed 50 characters") // VARCHAR(50)
    private String title;

    @NotBlank(message = "Contents cannot be empty") // NOT NULL
    private String contents;
}
