package org.wcci.blog.storage.Respositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.Models.Category;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findCategoryByName(String categoryName);
    Optional<Category> findCategoryById(Long id);

}



