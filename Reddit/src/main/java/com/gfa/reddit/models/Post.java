package com.gfa.reddit.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private long score;
    private LocalDate created;
    @ManyToOne
    private User user;

    static User loggedUser;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.score = 0;
        this.created = LocalDate.now();
    }

    public Post() {
        this.created = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(User loggedUser) {
        Post.loggedUser = loggedUser;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", created=" + created +
                '}';
    }
}
