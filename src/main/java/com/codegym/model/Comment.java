package com.codegym.model;

import javax.persistence.*;
import java.sql.Date;


@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComment;
    private String userName;
    private String content;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "id_blog")
    private Blog blog;

    public Comment() {
    }

    public Comment(int idComment, String userName, String content, Date date, Blog blog) {
        this.idComment = idComment;
        this.userName = userName;
        this.content = content;
        this.date = date;
        this.blog = blog;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
