package com.pw3.aleatorypost.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "content", nullable = false, length = 5000)
    private String content;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;
    
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    public Post() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime datetime) {
        this.date = datetime;
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
        if (!(o instanceof Post)) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", category=" + category +
                ", content='" + content + '\'' +
                ", datetime=" + date +
                ", user=" + user +
                '}';
    }
}
