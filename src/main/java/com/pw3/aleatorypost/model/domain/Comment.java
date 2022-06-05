package com.pw3.aleatorypost.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;


@Entity
@Table(name = "comment")
public class Comment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "comment", nullable = false, length = 5000)
    private String comment;
    
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
    @OneToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    public Comment() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Comment)) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", datetime=" + date +
                ", user=" + user +
                ", post=" + post +
                '}';
    }
}
