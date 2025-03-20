package dev.jl.jpaperformanceconfigurations.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<PostModel, Long> {
    @Query("""
    SELECT COUNT(p) > 0 FROM PostModel p WHERE p.id >= 1
    """)
    Boolean existsPosts();
}
