package org.wcci.blog.Models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private String postTitle;
    private String hashtag;
    private String description;
    private String publishedDate;

    @ManyToOne
    private Category category;

    public String getPublishedDate() {
        return publishedDate;
    }

    public Category getCategory() {
        return category;
    }

    public Author getAuthor() {
        return author;
    }

    public Collection<Hashtag> getHashtags() {
        return hashtags;
    }

    @ManyToOne
    private Author author;

    @ManyToMany
    private Collection<Hashtag> hashtags;


    public Post(String title, String hashtag, String description, String publishedDate, Category category) {

        this.postTitle = title;
        this.hashtag = hashtag;
        this.description = description;
        this.publishedDate = publishedDate;
        this.category = category;
    }
    public Post(String title){
         this.postTitle = title;
    }

    public Post() {
    }

    public String getPostTitle() {

        return postTitle;
    }

    public String getHashtag() {
        return hashtag;
    }


    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(postTitle, post.postTitle) &&
                Objects.equals(description, post.description) &&
                Objects.equals(hashtag, post.hashtag) &&
                Objects.equals(publishedDate, post.publishedDate) &&
                Objects.equals(category, post.category) &&
                Objects.equals(author, post.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postTitle, description, hashtag, publishedDate, category, author);
    }
}