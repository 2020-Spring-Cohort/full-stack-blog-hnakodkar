package org.wcci.blog.storage;

import org.wcci.blog.Models.Category;
import org.wcci.blog.Models.Hashtag;

import java.util.Collection;

public interface HashtagStorage {
    Collection<Hashtag> findAllhashtags();

    void store(Category category);

    Category findHashtagById(Long id);

}
