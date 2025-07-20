package study.assignment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.assignment.Domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 기본 CRUD 메서드 작성할 필요 X. JPA에서 알아서 제공해줌.
}
