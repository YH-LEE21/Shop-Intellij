package com.shop.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.dto.BlogDto;
import com.shop.entity.QBlog;
import com.shop.dto.QBlogDto;

import javax.persistence.EntityManager;
import java.util.List;

public class BlogRepositoryCustomImpl implements BlogRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public BlogRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<BlogDto> getMainBlogPage() {
        QBlog blog = QBlog.blog;

        QueryResults<BlogDto> results = queryFactory
                .select(
                        new QBlogDto(
                                blog.id,
                                blog.title,
                                blog.content,
                                blog.imgUrl1,
                                blog.imgUrl2,
                                blog.regTime,
                                blog.updateTime,
                                blog.member)
                )
                .from(blog)
                .orderBy(blog.id.desc())
                .fetchResults();

        return results.getResults();

    }
}