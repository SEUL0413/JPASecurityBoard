package com.seul.security.jpa.repository;

import com.seul.security.jpa.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Modifying
    @Query("update Posts p set p.count = p.count + 1 where p.id = :id")
    int updateView(Long id);

}
