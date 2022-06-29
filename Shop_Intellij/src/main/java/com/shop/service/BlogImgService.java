package com.shop.service;

import com.shop.entity.Blog;
import com.shop.entity.BlogImg;
import com.shop.repository.BlogImgRepository;
import com.shop.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogImgService {
    @Value("${blogImgLocation}")
    private String blogImgLocation;

    private final BlogImgRepository blogImgRepository;

    private final FileService fileService;

    public void saveBlogImg(Blog blog, BlogImg blogImg, MultipartFile itemImgFile, int count) throws Exception{
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //파일 업로드
        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(blogImgLocation, oriImgName,
                    itemImgFile.getBytes());
            imgUrl = "/images/blog/" + imgName;
            if(count == 0){
                blog.setImgUrl1(imgUrl);
            }else{
                blog.setImgUrl2(imgUrl);
            }
        }
        //상품 이미지 정보 저장
        blogImg.updateItemImg(oriImgName, imgName, imgUrl);
        blogImgRepository.save(blogImg);
    }

    public void updateBlogImg(Long itemImgId, MultipartFile itemImgFile) throws Exception{
        if(!itemImgFile.isEmpty()){
            BlogImg savedItemImg = blogImgRepository.findById(itemImgId)
                    .orElseThrow(EntityNotFoundException::new);

            //기존 이미지 파일 삭제
            if(!StringUtils.isEmpty(savedItemImg.getImgName())) {
                fileService.deleteFile(blogImgLocation+"/"+
                        savedItemImg.getImgName());
            }

            String oriImgName = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(blogImgLocation, oriImgName, itemImgFile.getBytes());
            String imgUrl = "/images/item/" + imgName;
            savedItemImg.updateItemImg(oriImgName, imgName, imgUrl);
        }
    }
}
