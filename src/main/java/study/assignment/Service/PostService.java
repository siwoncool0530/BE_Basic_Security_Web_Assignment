package study.assignment.Service;

import org.springframework.stereotype.Service;
import study.assignment.Domain.Post;
import study.assignment.Repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService { // 서비스: 비즈니스 로직 담당!
    private final PostRepository postRepository;

    // 생성자
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 게시글 작성
    public Post createPost(Post post) {
        post = Post.builder()
                .title(post.getTitle())
                .contents(post.getContents())
                .createdAt(LocalDateTime.now()) // 생성일시는 NULL일 수 없으므로 현재 시간으로 채우자.
                .build();
        return postRepository.save(post);
    }

    // 모든 게시글 리스트에 담아서 가져오기
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // ID를 기반으로 게시글 하나 가져오기
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found: " + id));
    }

    // 게시글 수정하기
    public Post updatePost(Long id, Post updatedPost) {
        Post post = getPostById(id);
        post.update(updatedPost.getTitle(), updatedPost.getContents());
        return postRepository.save(post);
    }

    // 게시글 삭제하기
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
