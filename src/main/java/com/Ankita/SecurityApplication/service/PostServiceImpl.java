package com.Ankita.SecurityApplication.service;


import com.Ankita.SecurityApplication.Exception.ResourceNotFoundException;
import com.Ankita.SecurityApplication.dto.PostDto;
import com.Ankita.SecurityApplication.entities.PostEntity;
import com.Ankita.SecurityApplication.entities.User;
import com.Ankita.SecurityApplication.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPosts(){

        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity,PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createNewPost(PostDto inputPost) {

        PostEntity postEntity=modelMapper.map(inputPost,PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity),PostDto.class);

    }

    @Override
    public PostDto getPostById(Long postId) {
//        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        log.info("user {}",user);

        PostEntity postEntity=postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException( "Post not found with id "+postId));
        return modelMapper.map(postEntity,PostDto.class);

    }

    @Override
    public PostDto updatePost(PostDto inputPost, long postId) {
        PostEntity olderPost=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post not found with id "+postId));
        inputPost.setPostId(postId);
        modelMapper.map(inputPost,olderPost);
        PostEntity savedPostEntity=postRepository.save(olderPost);
        return  modelMapper.map(savedPostEntity,PostDto.class);

    }

}
