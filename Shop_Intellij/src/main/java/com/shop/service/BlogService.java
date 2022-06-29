package com.shop.service;


import com.shop.entity.Blog;
import com.shop.entity.BlogImg;
import com.shop.dto.BlogFormDto;
import com.shop.entity.Member;
import com.shop.repository.BlogRepository;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogService {
    private final BlogRepository blogRepository;
    private  final BlogImgService blogImgService;
    private final ItemImgRepository itemImgRepository;
    private final MemberRepository memberRepository;

    private final FileService fileService;
    public Long saveBlog(BlogFormDto blogFormDto, List<MultipartFile> blogImgFileList, String email) throws Exception{
        //상품 등록
        Blog blog = blogFormDto.createBlog();

        Member member = memberRepository.findByEmail(email);
        blog.setMember(member);

        //이미지 등록
        for(int i =0;i<blogImgFileList.size();i++){
            BlogImg blogImg = new BlogImg();
            blogImg.setBlog(blog);
            blogImgService.saveBlogImg(blog,blogImg,blogImgFileList.get(i),i);
        }

        blogRepository.save(blog);
        return blog.getId();
    }



}


