package com.shop.repository;

import com.shop.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;


public interface BlogRepository extends JpaRepository<Blog, Long>, BlogRepositoryCustom, QuerydslPredicateExecutor<Blog> {
    List<Blog> findByTitle(String title);
    Blog findByMemberId(Long memberId);
}