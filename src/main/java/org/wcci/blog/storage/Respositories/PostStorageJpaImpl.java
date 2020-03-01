package org.wcci.blog.storage.Respositories;

import org.springframework.stereotype.Service;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.Models.Post;

import java.util.Collection;

@Service
public class PostStorageJpaImpl implements PostStorage {

    private PostRepository postRepository;


    public PostStorageJpaImpl(PostRepository postRepository) {

        this.postRepository = postRepository;
    }


    @Override
    public Collection<Post> findAllPosts() {
        return null;
    }

    @Override
    public void store(Post newPost) {
        postRepository.save(newPost);
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findById(id).get();
    }


}
