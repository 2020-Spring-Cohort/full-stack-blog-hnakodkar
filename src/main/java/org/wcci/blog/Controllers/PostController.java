package org.wcci.blog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wcci.blog.Models.Post;
import org.wcci.blog.storage.PostStorage;


@Controller
public class PostController {

    private PostStorage postStorage;
    
    public PostController(PostStorage postStorage) {
  
        this.postStorage = postStorage;
    }
    @RequestMapping("/posts")
    public String displayPosts(Model model) {
        model.addAttribute("posts", postStorage.findAllPosts());
        return "blogPage";
    }
    @GetMapping("/posts/{id}")
    public String displaySinglePost(@PathVariable Long id, Model model) {
      Post retrievedPost = postStorage.findPostById(id);
      model.addAttribute("post",retrievedPost);
      return "post";
    }
    @PostMapping("/add-post/")
    public String addPost(@RequestParam String postTitle, @RequestParam String hashtag, @RequestParam String description, @RequestParam String publishedDate) {
        postStorage.store(new Post(postTitle, hashtag, description, publishedDate,null ));
        return"redirect:posts";
    }

}

