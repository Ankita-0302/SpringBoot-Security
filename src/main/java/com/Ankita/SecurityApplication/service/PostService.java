package com.Ankita.SecurityApplication.service;

import com.Ankita.SecurityApplication.dto.PostDto;

import java.util.List;


public interface PostService {

    List<PostDto> getAllPosts();

    PostDto createNewPost(PostDto inputPost);

    PostDto getPostById(Long postId);


    PostDto updatePost(PostDto inputPost, long postId);
}
