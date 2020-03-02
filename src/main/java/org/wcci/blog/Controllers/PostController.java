package org.wcci.blog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wcci.blog.Models.Category;
import org.wcci.blog.Models.Hashtag;
import org.wcci.blog.Models.Post;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.HashtagStorage;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.storage.Respositories.HashtagRepository;

import java.util.Optional;




@Controller
public class PostController {

    private CategoryStorage categoryStorage;
    private PostStorage postStorage;
    private HashtagRepository hashtagRepo;

    
    public PostController(PostStorage postStorage, CategoryStorage categoryStorage, HashtagRepository hashtagRepo) {
  
        this.postStorage = postStorage;
        this.categoryStorage = categoryStorage;
        this.hashtagRepo = hashtagRepo;
    }


    @GetMapping("/post/{id}")
    public String displaySinglePost(@PathVariable Long id, Model model) {
      Post retrievedPost = postStorage.findPostById(id);
      model.addAttribute("post",retrievedPost);
      return "post";
    }

    @PostMapping("/post/{id}/add-hashtag")
    public String addHashtagToPost(@RequestParam String hashtag,@PathVariable Long id) {
        Hashtag hashtagToAddToPost;
        Optional<Hashtag> hashTagToAddOpt = hashtagRepo.findByName(hashtag);
        if (hashTagToAddOpt.isEmpty()) {
            hashtagToAddToPost = new Hashtag(hashtag);
            hashtagRepo.save(hashtagToAddToPost);
        } else {
            hashtagToAddToPost = hashTagToAddOpt.get();
        }
        Post postToAddHashtagTo = postStorage.findPostById(id);
        postToAddHashtagTo.addHashtag(hashtagToAddToPost);
        postStorage.store(postToAddHashtagTo);
        return "redirect:/post/" + id;

    }
}

