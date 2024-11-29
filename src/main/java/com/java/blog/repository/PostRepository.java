package com.java.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.blog.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
