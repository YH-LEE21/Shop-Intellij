package com.shop.dto;

import com.shop.entity.Blog;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
public class BlogFormDto {

    private Long id;

    @NotBlank(message = "리뷰 제목은 필수 입력 값입니다.")
    private String title;

    @NotNull(message = "리뷰 내용은 필수 입력 값입니다.")
    private String content;

    private List<BlogImgDto> blogImgDtoList = new ArrayList<>();

    private List<Long> blogImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Blog createBlog(){
        return modelMapper.map(this,Blog.class);
    }

    public static BlogFormDto of(Blog blog){
        return modelMapper.map(blog,BlogFormDto.class);
    }
}
