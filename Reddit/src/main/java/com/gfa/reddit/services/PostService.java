package com.gfa.reddit.services;

import com.gfa.reddit.models.Post;
import com.gfa.reddit.models.User;
import com.gfa.reddit.repositories.PagingRepository;
import com.gfa.reddit.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    @Autowired
    PagingRepository repository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void incrementScore (Long id) {
        Post post = postRepository.findById(id).get();
        post.setScore(postRepository.findById(id).get().getScore()+1);
    }

    public void decrementScore (Long id) {
        Post post = postRepository.findById(id).get();
        post.setScore(postRepository.findById(id).get().getScore()-1);
    }

    public List<Post> getAllPosts(Integer pageNo, Integer pageSize, String sortBy, Model model)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        Page<Post> pagedResult = repository.findAll(paging);
        model.addAttribute("allPosts", postRepository.findAll());
        model.addAttribute("isUserLoggedIn", User.isIsLoginValid());
        if (User.isIsLoginValid()) {
            model.addAttribute("loggedUsername", Post.getLoggedUser().getUsername());
        }
        if(pagedResult.hasContent()) {
            model.addAttribute("posts", pagedResult.getContent());
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    public ResponseEntity<List<Post>> getAllPosts(Model model,
                                                  @RequestParam(defaultValue = "0") Integer pageNo,
                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                  @RequestParam(defaultValue = "score") String sortBy)
    {
        List<Post> list = getAllPosts(pageNo, pageSize, sortBy, model);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    public void deletePost(Long id) {
        if (postRepository.findById(id).get().getUser() == Post.getLoggedUser()) {
            postRepository.deleteById(id);
        }
    }

}
