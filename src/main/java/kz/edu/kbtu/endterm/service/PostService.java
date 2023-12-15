package kz.edu.kbtu.endterm.service;

import kz.edu.kbtu.endterm.dto.request.PostDtoRequest;
import kz.edu.kbtu.endterm.model.Post;
import kz.edu.kbtu.endterm.model.User;
import kz.edu.kbtu.endterm.repository.PostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo repository;
    private final UserService userService;

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Post create(PostDtoRequest request, String username) {

        return repository.save(Post.builder()
                                   .title(request.getTitle())
                                   .text(request.getText())
                                   .author((User) userService.loadUserByUsername(username))
                                   .build());
    }

    public Post update(Long id, PostDtoRequest request) {
        final var post = repository.findById(id)
                                   .orElseThrow(() -> new IllegalArgumentException(
                                           String.format("Post with id %s is not found!", id)));

        post.setTitle(request.getTitle());
        post.setText(request.getText());

        return repository.save(post);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
