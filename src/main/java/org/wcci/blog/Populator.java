package org.wcci.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wcci.blog.Models.Category;
import org.wcci.blog.Models.Post;
import org.wcci.blog.storage.Respositories.CategoryRepository;
import org.wcci.blog.storage.Respositories.PostRepository;

@Component
public class Populator implements CommandLineRunner {


    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private PostRepository postRepo;

    @Override
    public void run(String... args) throws Exception {

        Category newCat = new Category("Fashion");
        categoryRepo.save(newCat);
        Post newPost = new Post("women fashion", "#cool", "its really awesome", "02/12/2020", newCat);
        postRepo.save(newPost);

        Category newCat2 = new Category("Cars");
        categoryRepo.save(newCat2);
        Post newPost2 = new Post("Sports Cars", "#cool", "they are really fast", "03/01/2020", newCat2);
        postRepo.save(newPost2);


    }

}
