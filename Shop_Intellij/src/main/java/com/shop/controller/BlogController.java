package com.shop.controller;

import com.shop.dto.BlogDto;
import com.shop.dto.BlogFormDto;
import com.shop.repository.BlogRepository;
import com.shop.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;
    private final BlogRepository blogRepository;

    @GetMapping(value = "/blogList")
    public String blog(Model model){
        List<BlogDto> list = blogRepository.getMainBlogPage();
        model.addAttribute("blogItems",list);
        return "blog/blogForm";
    }

    @GetMapping(value = "/blogList/new")
    public String blogItemForm(Model model)
    {
        model.addAttribute("BlogFormDto",new BlogFormDto());
        return "blog/blogNew";
    }
    @PostMapping(value = "/blogList/new")
    public String blogItemNew(@Valid BlogFormDto blogFormDto, BindingResult bindingResult,
                          Model model, @RequestParam("blogImgFile") List<MultipartFile> blogImgFileList,Principal principal){

        if(bindingResult.hasErrors()){
            return "blog/blogNew";
        }

        try {
            blogService.saveBlog(blogFormDto, blogImgFileList, principal.getName());
        } catch (Exception e){
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "blog/blogNew";
        }

        return "redirect:/";
    }
}
