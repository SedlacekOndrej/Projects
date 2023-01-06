package com.gfa.reddit.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Post> postList;
    static boolean isLoginValid;

    public User(String userName, String password) {
        this.username = userName;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public static boolean isIsLoginValid() {
        return isLoginValid;
    }

    public static void setIsLoginValid(boolean isLoginValid) {
        User.isLoginValid = isLoginValid;
    }

    @Override
    public String toString() {
        return username;
    }
}
