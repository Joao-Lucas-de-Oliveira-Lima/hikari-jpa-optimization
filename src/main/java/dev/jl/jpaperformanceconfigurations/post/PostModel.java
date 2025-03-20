package dev.jl.jpaperformanceconfigurations.post;

import dev.jl.jpaperformanceconfigurations.user.UserModel;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Objects;

@Entity
@Table(name = "post")
@DynamicUpdate
public class PostModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    @Id
    private Long id;
    @Column(name = "user_id", nullable = false)
    @Transient
    private Long userId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String body;
    @Version
    private Long version;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel user;


    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PostModel model = (PostModel) object;
        return Objects.equals(userId, model.userId) && Objects.equals(title, model.title) && Objects.equals(body, model.body) && Objects.equals(user, model.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, title, body, user);
    }
}
