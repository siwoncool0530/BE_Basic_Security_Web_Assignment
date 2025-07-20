package study.assignment.Domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 자동 생성하는 값
    private Long postId;

    @Column(nullable = false, length = 50) // 제목. NOT NULL, VARCHAR(50)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT") // 내용. NOT NULL, TEXT
    private String contents;

    @Column(nullable = false) // 생성일. NOT NULL
    private LocalDateTime createdAt;

    @Column // 수정일
    private LocalDateTime updatedAt;

    @Builder // 생성자
    public Post(String title, String contents, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // 업데이트용
    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
        this.updatedAt = LocalDateTime.now();
    }

}