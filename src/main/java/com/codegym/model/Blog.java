package com.codegym.model;

import javax.persistence.*;

@Entity
public class Blog {
    @Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idBlog;
    private String imgSrc;
    private String title;
    @Column(length = 10000000)
    private String content;
    @Column(length = 10000000)
    private String contentDisplay;

    public String getContentDisplay() {
        return contentDisplay;
    }

    public void setContentDisplay(String contentDisplay) {
        this.contentDisplay = contentDisplay;
    }

    public Blog(int idBlog, String imgSrc, String title, String content, String contentDisplay) {
        this.idBlog = idBlog;
        this.imgSrc = imgSrc;
        this.title = title;
        this.content = content;
        this.contentDisplay = contentDisplay;
    }

    public Blog() {
    }

    public int getIdBlog() {
        return idBlog;
    }

    public void setIdBlog(int idBlog) {
        this.idBlog = idBlog;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
