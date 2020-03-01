package org.wcci.blog.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Hashtag {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany()
    private Collection<Post> posts;

    public Hashtag() {
    }

    public Hashtag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
