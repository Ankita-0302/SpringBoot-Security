package com.Ankita.SecurityApplication.controllers;


import com.Ankita.SecurityApplication.dto.PostDto;
import com.Ankita.SecurityApplication.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDto createNewPost(@RequestBody PostDto inputPost){

        return postService.createNewPost(inputPost);
    }

    @PutMapping("/{postId}")
    public PostDto updatePost(@RequestBody PostDto inputPost,@PathVariable long postId){

        return postService.updatePost(inputPost,postId);
    }

}
