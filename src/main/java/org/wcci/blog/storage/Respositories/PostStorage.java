package org.wcci.blog.storage.Respositories;

import org.wcci.blog.Models.Post;

public interface PostStorage  {
    Post findPostById(Long id);

    void store(Post newPost);
}
