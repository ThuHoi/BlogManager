package com.codegym.controller;

import com.codegym.model.Comment;
import com.codegym.repository.BlogRepo;
import com.codegym.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.codegym.service.CommentService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Value("${file-upload}")
    private String fileUpload;
    @Autowired
    BlogRepo blogRepo;

    @Autowired
    CommentService commentService;

    @GetMapping("")
    public ModelAndView showBlog() {
        ModelAndView modelAndView = new ModelAndView("index");
        List<Blog> blogs = blogRepo.getList();
        modelAndView.addObject("blog", blogs);
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Blog blog, @RequestParam(value = "file", required = false) MultipartFile file) {
        if (file.getSize() != 0) {
            String nameImg = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(fileUpload + "img/" + nameImg));
            } catch (IOException e) {
                e.printStackTrace();
            }
            blog.setImgSrc("/img/" + nameImg);
            blogRepo.save(blog);
        }
        return "redirect:/blog";
    }

    @GetMapping("/post/{id}")
    public String showPost(@PathVariable int id, Model model) {
        Iterable<Comment> comments = commentService.findAll(blogRepo.findById(id));
        model.addAttribute("comment", comments);
        model.addAttribute("blog", blogRepo.findById(id));
        return "single-post";
    }

    @PostMapping("/post/{id}")
    public String comment(@PathVariable int id, Model model,@RequestParam("userName") String userName,
                          @RequestParam("content") String content){
        Blog blog = blogRepo.findById(id);
        Comment comment = new Comment();
        comment.setBlog(blog);
        comment.setContent(content);
        comment.setUserName(userName);
        commentService.save(comment);

        model.addAttribute("blog", blogRepo.findById(id));
        Iterable<Comment> comments = commentService.findAll(blogRepo.findById(id));
        model.addAttribute("comment", comments);
        return "single-post";

    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable int id, Model model) {

        model.addAttribute("blog", blogRepo.findById(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute Blog blog, @RequestParam(value = "file", required = false) MultipartFile file) {
        if (file.getSize() != 0) {
            try {
                File file1 = new File(fileUpload + blog.getImgSrc());
                file1.deleteOnExit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String nameImg = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(fileUpload + "img/" + nameImg));
            } catch (IOException e) {
                e.printStackTrace();
            }
            blog.setImgSrc("/img/" + nameImg);

        }
        blogRepo.edit(blog);
        return "single-post";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("blog", blogRepo.findById(id));
        return "/delete";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam int idProduct, RedirectAttributes redirect, @RequestParam String img) {
        try {
            File file1 = new File(fileUpload + img);
            file1.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        blogRepo.delete(idProduct);
        redirect.addFlashAttribute("success", "Removed blog successfully!");
        return "redirect:/product";
    }

    @PostMapping("/search")
    public String search(@RequestParam(value = "search",required = false) String search, Model model) {
        List<Blog> products = blogRepo.findByName(search);
        ;
        model.addAttribute("product", products);
        return "/index";
    }


}