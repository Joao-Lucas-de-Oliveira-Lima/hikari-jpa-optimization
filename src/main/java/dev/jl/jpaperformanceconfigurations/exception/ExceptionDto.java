package dev.jl.jpaperformanceconfigurations.exception;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@JsonPropertyOrder(alphabetic = true)
public class ExceptionDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String instance;
    private String detail;
    private Instant timestamp;
    private Integer status;
    private String title;

    public ExceptionDto(String instance, String detail, Instant timestamp, Integer status, String title) {
        this.instance = instance;
        this.detail = detail;
        this.timestamp = timestamp;
        this.status = status;
        this.title = title;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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
        ExceptionDto that = (ExceptionDto) object;
        return Objects.equals(instance, that.instance) && Objects.equals(detail, that.detail) && Objects.equals(timestamp, that.timestamp) && Objects.equals(status, that.status) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instance, detail, timestamp, status, title);
    }
}
