package com.codegym.repository;

import com.codegym.model.Blog;
import com.codegym.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Integer> {
    List<Comment> findByBlog(Blog blog);

}
