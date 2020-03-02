package org.wcci.blog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcci.blog.Models.Category;
import org.wcci.blog.Models.Post;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;

import java.util.HashMap;

@Controller
public class CategoryController {

    private final CategoryStorage categoryStorage;
    private PostStorage postStorage;

    public CategoryController(CategoryStorage categoryStorage,PostStorage postStorage, AuthorStorage authorStorage) {

        this.categoryStorage = categoryStorage;
    }

    @RequestMapping("/categories")
    public String displayCategories(Model model) {
        model.addAttribute("categories", categoryStorage.findAllCategories());
        return "blogPage";
    }

    @RequestMapping("/categories/{categoryName}")
    public String displaySingleCategory(@PathVariable String categoryName, Model model) {
        Category retrieveCategory = categoryStorage.findCategoryByName(categoryName);
        model.addAttribute("category", retrieveCategory);
        return "category";
    }

    @PostMapping("/add-category")
    public String addCategory(@RequestParam String categoryName) {
        categoryStorage.store(new Category(categoryName));
        return"redirect:categories";
    }

}
