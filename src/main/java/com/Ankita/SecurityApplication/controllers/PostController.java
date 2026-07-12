package com.Ankita.SecurityApplication.controllers;



import com.Ankita.SecurityApplication.dto.PostDto;
import com.Ankita.SecurityApplication.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public List<PostDto> getAllPosts(){

        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    //@PreAuthorize("hasAnyRole('USER','ADMIN') OR hasAuthority('POST_VIEW')")
    @PreAuthorize("@postSecurity.isOwnerOfPost(#postId)")
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
