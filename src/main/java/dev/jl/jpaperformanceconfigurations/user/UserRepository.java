package dev.jl.jpaperformanceconfigurations.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    @Query("""
                SELECT COUNT(u) > 0 FROM UserModel u WHERE u.id >= 1
            """)
    Boolean existsUsers();
}
