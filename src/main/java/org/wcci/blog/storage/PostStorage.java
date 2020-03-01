package org.wcci.blog.storage;

import org.wcci.blog.Models.Post;

import java.util.Collection;

public interface PostStorage {
    Collection<Post>findAllPosts();
    void store(Post newPost);

    Post findPostById(Long id);
}

