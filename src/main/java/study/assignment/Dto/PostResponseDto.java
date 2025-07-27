package study.assignment.Dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostResponseDto {
    private final Long postId;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
