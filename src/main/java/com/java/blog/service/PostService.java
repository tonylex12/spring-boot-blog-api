package com.java.blog.service;

import java.util.List;

import com.java.blog.payload.PostDto;

public interface PostService {
  PostDto createPost(PostDto postDto);

  List<PostDto> getAllPosts(int pageNo, int pageSize);

  PostDto getPostById(Long id);

  PostDto updatePost(PostDto postDto, Long id);

  void deletePostById(Long id);

}
