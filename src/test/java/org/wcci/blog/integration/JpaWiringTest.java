package org.wcci.blog.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.wcci.blog.Models.Category;
import org.wcci.blog.Models.Post;
import org.wcci.blog.storage.Respositories.CategoryRepository;
import org.wcci.blog.storage.Respositories.HashtagRepository;
import org.wcci.blog.storage.Respositories.PostRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaWiringTest {
    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private HashtagRepository hashtagRepo;

    @Autowired
    private TestEntityManager entityManager;

@Test
    public void contextLoads(){

}

    @Test
    public void categoryShouldHaveAListOfPosts() {
        Category testCategory = new Category("Shoes");
        Post testPost = new Post("MyPost",  "#cool", "its really awesome","01/25/19",testCategory );
        categoryRepo.save(testCategory);
        postRepo.save(testPost);
        entityManager.flush();
        entityManager.clear();
        Optional<Category> retrievedCategoryOptional = categoryRepo.findCategoryByName(testCategory.getName());
        Category retrievedCategory = retrievedCategoryOptional.get();
        Post retrievedPost = postRepo.findById(testPost.getId()).get();
        assertThat(retrievedCategory.getPosts()).contains(testPost);

    }

    }