package com.shop.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table(name="blog_img")
@Getter
@Setter
public class BlogImg extends BaseEntity {
    @Id
    @Column(name="blog_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgName; //이미지 파일명

    private String oriImgName; //원본 이미지 파일명

    private String imgUrl; //이미지 조회 경로

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id")
    private Blog blog;

    public void updateItemImg(String oriImgName, String imgName, String imgUrl){
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }

}
