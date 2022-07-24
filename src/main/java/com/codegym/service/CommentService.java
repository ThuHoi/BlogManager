package com.codegym.service;

import com.codegym.model.Blog;
import com.codegym.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codegym.repository.CommentRepo;

import java.util.List;


@Service
public class CommentService {
    @Autowired
    CommentRepo commentRepo;

    public List<Comment> findAll(Blog blog) {
        return commentRepo.findByBlog(blog);
    }

    public void save(Comment comment){
        commentRepo.save(comment);
    }

}
