package com.shop.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// id title content imgfile1,imgfile2
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Blog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false,length = 1000)
    private String content;

    private String imgUrl1; //블로그 이미지1 조회 경로
    private String imgUrl2; //블로그 이미지2 조회 경로

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


}
