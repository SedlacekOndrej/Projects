package com.gfa.reddit.controllers;

import com.gfa.reddit.models.Post;
import com.gfa.reddit.repositories.PostRepository;
import com.gfa.reddit.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    @Autowired
    public PostController(PostService postService, PostRepository postRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    public String renderIndexPage(Model model,
                                  @RequestParam(defaultValue = "0") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestParam(defaultValue = "score") String sortBy) {
        postService.getAllPosts(model, pageNo, pageSize, sortBy);
        return "index";
    }

    @GetMapping("/submit")
    public String renderSubmitPage() {
        return "submit";
    }

    @PostMapping("/submit")
    public String submitNewPost(@ModelAttribute Post post) {
        post.setUser(Post.getLoggedUser());
        postRepository.save(post);
        return "redirect:/";
    }

    @GetMapping("/{id}/increment")
    public String incrementScore(@PathVariable Long id) {
        postService.incrementScore(id);
        postRepository.save(postRepository.findById(id).get());
        return "redirect:/";
    }

    @GetMapping("/{id}/decrement")
    public String decrementScore(@PathVariable Long id) {
        postService.decrementScore(id);
        postRepository.save(postRepository.findById(id).get());
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/";
    }

}
