package study.assignment.Controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import study.assignment.Dto.PostCreateDto;
import study.assignment.Dto.PostResponseDto;
import study.assignment.Dto.PostUpdateDto;
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

    // 게시글 작성 (Post) - PostCreateDto를 요청으로 받아서 PostResponse로 반환
    @PostMapping
    public PostResponseDto create(@Valid @RequestBody PostCreateDto postCreateDto) {
        return postService.createPost(postCreateDto);
    }

    // 게시글 목록 조회 (Get) - List를 반환할 때 PostResponseDto로 반환
    @GetMapping
    public List<PostResponseDto> getAll() {
        return postService.getAllPosts();
    }

    // id를 기반으로 특정 게시글 가져오기 (Get) - PostResponseDto로 반환
    @GetMapping("/{id}")
    public PostResponseDto getById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    // id를 기반으로 특정 게시글 수정하기 (Patch) - PostUpdateDto를 요청으로 받아서 PostResponse로 변환
    @PatchMapping("/{id}")
    public PostResponseDto update(@PathVariable Long id, @RequestBody PostUpdateDto postUpdateDto){
        return postService.updatePost(id, postUpdateDto);
    }

    // id를 기반으로 특정 게시글 삭제하기 (Delete)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
