package org.wcci.blog.storage.Respositories;

import org.springframework.stereotype.Service;
import org.wcci.blog.Models.Category;
import org.wcci.blog.Models.Hashtag;
import org.wcci.blog.storage.HashtagStorage;

import java.util.Collection;

@Service
public class HashtagStorageJpaImpl implements HashtagStorage {



    @Override
    public Collection<Hashtag> findAllhashtags() {
        return null;
    }

    @Override
    public void store(Category category) {

    }

    @Override
    public Category findHashtagById(Long id) {
        return null;
    }
}
