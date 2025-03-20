package dev.jl.jpaperformanceconfigurations.post;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;


@JsonPropertyOrder(alphabetic = true)
public class PostPatchDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PostPatchDto that = (PostPatchDto) object;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
