package org.wcci.blog.storage;

import org.wcci.blog.Models.Post;

import java.util.Collection;

public interface PostStorage {
     void store(Post postToStore);


    Collection<Post>findAllPosts();

    Post findPostById(Long id);
}

