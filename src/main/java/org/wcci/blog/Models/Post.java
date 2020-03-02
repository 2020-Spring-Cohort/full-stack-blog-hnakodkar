package org.wcci.blog.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Category category;
    private String postTitle;
    private String description;
    private String publishedDate;

    @ManyToOne
    private Author author;

    @ManyToMany
    private Collection<Hashtag> hashtags;


    public Post(String title, String hashtag, String description, String publishedDate, Category category, Hashtag...hashtags) {

        this.postTitle = title;
        this.description = description;
        this.publishedDate = publishedDate;
        this.category = category;
        this.hashtags = new ArrayList<Hashtag>(Arrays.asList(hashtags));
    }
    public Post(String title){
         this.postTitle = title;
    }

    public Post(){}


    public Post(Category retrieveCategory, String postTitle, String description, String publishedDate) {
    }


    public String getPostTitle() {

        return postTitle;
    }

    public Collection<Hashtag>getHashtags(){
        return hashtags;
    }



    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void addHashtag(Hashtag hashtagToAddToPost) {
        if (!this.hashtags.contains(hashtagToAddToPost)) {
            hashtags.add(hashtagToAddToPost);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(category, post.category) &&
                Objects.equals(postTitle, post.postTitle) &&
                Objects.equals(description, post.description) &&
                Objects.equals(publishedDate, post.publishedDate) &&
                Objects.equals(author, post.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, postTitle, description, publishedDate, author);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", category=" + category +
                ", postTitle='" + postTitle + '\'' +
                ", description='" + description + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", author=" + author +
                '}';
    }
}