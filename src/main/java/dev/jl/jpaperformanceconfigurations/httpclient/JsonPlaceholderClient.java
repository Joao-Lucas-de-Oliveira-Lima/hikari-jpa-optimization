package dev.jl.jpaperformanceconfigurations.httpclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;

@FeignClient(name = "JsonPlaceholder", url = "https://jsonplaceholder.typicode.com")
public interface JsonPlaceholderClient {

    @GetMapping("/posts")
    List<Post> fetchPosts();

    @GetMapping("/users")
    List<User> fetchUsers();

    class User {
        private Long id;
        private String name;
        private String username;
        private String email;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            User user = (User) object;
            return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(username, user.username) && Objects.equals(email, user.email);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, username, email);
        }
    }

    class Post {
        private Long userId;
        private Long id;
        private String title;
        private String body;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
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

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Post post = (Post) object;
            return Objects.equals(userId, post.userId) && Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(body, post.body);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, id, title, body);
        }
    }
}
