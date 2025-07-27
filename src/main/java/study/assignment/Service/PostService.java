package study.assignment.Service;

import org.springframework.stereotype.Service;
import study.assignment.Domain.Post;
import study.assignment.Dto.PostCreateDto;
import study.assignment.Dto.PostResponseDto;
import study.assignment.Dto.PostUpdateDto;
import study.assignment.Repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService { // 서비스: 비즈니스 로직 담당!
    private final PostRepository postRepository;

    // 생성자
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 게시글 작성
    public PostResponseDto createPost(PostCreateDto postCreateDto) {
        Post post = Post.builder()
                .title(postCreateDto.getTitle())
                .contents(postCreateDto.getContents()) // DTO에서 제목과 내용을 가져옴
                .createdAt(LocalDateTime.now()) // 생성일시는 NULL일 수 없으므로 현재 시간으로 채우자.
                .build();
        Post savedPost = postRepository.save(post); // 레포지토리에 저장
        // Post Entity를 Dto로 변환하여 반환
        return new PostResponseDto(
                savedPost.getPostId(),
                savedPost.getTitle(),
                savedPost.getContents(),
                savedPost.getCreatedAt(),
                savedPost.getUpdatedAt()
        );
    }

    // 모든 게시글 리스트에 담아서 가져오기
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> new PostResponseDto(
                post.getPostId(),
                post.getTitle(),
                post.getContents(),
                post.getCreatedAt(),
                post.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

    // ID를 기반으로 게시글 하나 가져오기
    public PostResponseDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found: " + id));
        return new PostResponseDto(
                post.getPostId(),
                post.getTitle(),
                post.getContents(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }

    // ID 기반으로 게시글의 "엔티티"를 가져오기 (서비스 내부에서만 사용.)
    public Post getPostEntityById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found: " + id));
    }

    // 게시글 수정하기
    public PostResponseDto updatePost(Long id, PostUpdateDto postUpdateDto) {
        Post post = getPostEntityById(id); // 기존 Post Entity를 조회하자.
        post.update(postUpdateDto.getTitle(), postUpdateDto.getContents());
        Post updatedPost = postRepository.save(post); // 수정된 Entity를 레포지토리에 저장
        // dto로 변환하여 반환
        return new PostResponseDto(
                updatedPost.getPostId(),
                updatedPost.getTitle(),
                updatedPost.getContents(),
                updatedPost.getCreatedAt(),
                updatedPost.getUpdatedAt()
        );
    }

    // 게시글 삭제하기
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) { // 존재하는지 확인 후 삭제
            throw new IllegalArgumentException("Post not found: " + id);
        }
        postRepository.deleteById(id);
    }
}
