package org.wcci.blog.storage.Respositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.Models.Hashtag;

import java.util.Optional;

public interface HashtagRepository extends CrudRepository<Hashtag, Long> {

    Optional<Hashtag> findByName(String hashtag);
}
