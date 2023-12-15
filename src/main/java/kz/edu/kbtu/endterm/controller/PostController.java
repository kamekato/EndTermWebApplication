package kz.edu.kbtu.endterm.controller;

import kz.edu.kbtu.endterm.dto.request.PostDtoRequest;
import kz.edu.kbtu.endterm.model.Post;
import kz.edu.kbtu.endterm.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<Post> getPosts() {
        return postService.findAll();
    }

    @PostMapping
    public Post create(@RequestBody PostDtoRequest request, Principal principal) {
        return postService.create(request, principal.getName());
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody PostDtoRequest request) {
        return postService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }

}
