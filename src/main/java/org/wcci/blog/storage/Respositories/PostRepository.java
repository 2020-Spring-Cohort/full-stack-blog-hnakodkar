package org.wcci.blog.storage.Respositories;


import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.Models.Post;


public interface PostRepository extends CrudRepository<Post, Long> {


}

