package study.assignment.Controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import study.assignment.Domain.Post;
import study.assignment.Dto.PostCreateDto;
import study.assignment.Service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts") // 사용자가 /api/posts에 요청할 때 컨트롤러 실행!
public class PostController { // 컨트롤러: 중간 연결자 역할!
    private final PostService postService;

    // 생성자
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 작성 (Post)
    @PostMapping
    public Post create(@Valid @RequestBody PostCreateDto postCreateDto) {
        return postService.createPost(postCreateDto);
    }

    // 게시글 목록 조회 (Get)
    @GetMapping
    public List<Post> getAll() {
        return postService.getAllPosts();
    }

    // id를 기반으로 특정 게시글 가져오기 (Get)
    @GetMapping("/{id}")
    public Post getById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    // id를 기반으로 특정 게시글 수정하기 (Patch)
    @PatchMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody Post updatedPost){
        return postService.updatePost(id, updatedPost);
    }

    // id를 기반으로 특정 게시글 삭제하기 (Delete)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
