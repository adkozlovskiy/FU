package kozlovskiy.blog.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String login;

    @Column(unique = true)
    private String email;

    private String password;

    private boolean isActive;

    @OneToMany(mappedBy = "author")
    private Set<Post> userPosts;

    @OneToMany(mappedBy = "author")
    private Set<Comment> comments;

    @ManyToMany
    private Set<User> likedPosts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Post> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(Set<Post> userPosts) {
        this.userPosts = userPosts;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<User> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(Set<User> likedPosts) {
        this.likedPosts = likedPosts;
    }
}