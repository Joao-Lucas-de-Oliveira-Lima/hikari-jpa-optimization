package dev.jl.jpaperformanceconfigurations.post;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder(alphabetic = true)
public class PostResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String body;
    private Long version;

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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PostResponseDto that = (PostResponseDto) object;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(body, that.body) && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, body, version);
    }
}
