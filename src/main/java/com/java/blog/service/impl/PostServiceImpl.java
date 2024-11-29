package com.java.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.java.blog.entity.Post;
import com.java.blog.exception.ResourceNotFoundException;
import com.java.blog.payload.PostDto;
import com.java.blog.repository.PostRepository;
import com.java.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

  private PostRepository postRepository;

  public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public PostDto createPost(PostDto postDto) {

    Post post = mapToEntity(postDto);

    Post newPost = postRepository.save(post);

    PostDto postResponse = mapToDto(newPost);

    return postResponse;

  }

  @Override
  public List<PostDto> getAllPosts() {
    List<Post> posts = postRepository.findAll();
    return posts.stream().map(this::mapToDto).collect(Collectors.toList());
  }

  private PostDto mapToDto(Post post) {
    PostDto postDto = new PostDto();
    postDto.setId(post.getId());
    postDto.setTitle(post.getTitle());
    postDto.setDescription(post.getDescription());
    postDto.setContent(post.getContent());
    return postDto;
  }

  private Post mapToEntity(PostDto postDto) {
    Post post = new Post();
    post.setId(postDto.getId());
    post.setTitle(postDto.getTitle());
    post.setDescription(postDto.getDescription());
    post.setContent(postDto.getContent());
    return post;
  }

  @Override
  public PostDto getPostById(Long id) {
    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    return mapToDto(post);
  }

  @Override
  public PostDto updatePost(PostDto postDto, Long id) {
    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

    post.setTitle(postDto.getTitle());
    post.setDescription(postDto.getDescription());
    post.setContent(postDto.getContent());

    Post updatedPost = postRepository.save(post);

    return mapToDto(updatedPost);
  }

  @Override
  public void deletePostById(Long id) {
    Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    postRepository.delete(post);
  }
}
