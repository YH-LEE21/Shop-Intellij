package com.shop.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.shop.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BlogDto {
    private Long id;

    private String title;

    private String content;

    private Member member;

    private String imgUrl1; //상품 이미지 경로
    private String imgUrl2; //상품 이미지 경로

    private LocalDateTime regTime;

    private LocalDateTime updateTime;


    @QueryProjection
    public BlogDto(Long id, String title, String content, String imgUrl1, String imgUrl2, LocalDateTime regTime, LocalDateTime updateTime, Member member){
        this.id = id;
        this.title = title;
        this.content = content;
        this.regTime = regTime;
        this.imgUrl1 = imgUrl1;
        this.imgUrl2 = imgUrl2;
        this.updateTime = updateTime;
        this.member = member;
    }
}
