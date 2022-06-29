package com.shop.api;


import com.shop.repository.BlogRepository;
import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ItemService itemService;
    BlogRepository blogRepository;


    @GetMapping("/productList")
    public Page<MainItemDto> productList(){
        Pageable pageable = PageRequest.of(0,5);
        ItemSearchDto itemSearchDto = new ItemSearchDto();
        Page<MainItemDto> items=
                itemService.getMainItemPage(itemSearchDto,pageable);
        return items;
    }

    @PostMapping("/productList")
    public Page<MainItemDto> productList2(){
        Pageable pageable = PageRequest.of(0,5);
        ItemSearchDto itemSearchDto = new ItemSearchDto();
        Page<MainItemDto> items=
                itemService.getMainItemPage(itemSearchDto,pageable);
        return items;
    }

    @GetMapping("productDetail/{itemId}")
    public ItemFormDto itemDetail(@PathVariable("itemId") Long itemId){
        ItemFormDto itemFormDto = itemService.getItemDtl(itemId);
        return itemFormDto;
    }
    @PostMapping("testInsert")
    public String testInsert(User user){
        System.out.println("이름: "+user.getName());
        System.out.println("이메일: "+user.getEmail());
        System.out.println("주소: "+user.getAdress());
        System.out.println("tel: "+user.getTel());
        System.out.println("================= 저장 성공");
        return "저장 성공";
    }

    @PostMapping("productInsert")
    public String productInsert(@RequestBody VueFormDto vueFormDto){
        System.out.println("상품이름: "+vueFormDto.getItemNm());
        System.out.println("상품가격: "+vueFormDto.getPrice());
        System.out.println("상품재고: "+vueFormDto.getStockNumber());
        System.out.println("상품설명: "+vueFormDto.getItemDetail());
        System.out.println("file1: "+vueFormDto.getFile1());
        System.out.println("file2: "+vueFormDto.getFile2());


        try{
            List<MultipartFile> itemImgFileList = new ArrayList<>();
            itemImgFileList.add(vueFormDto.getFile1());
            itemImgFileList.add(vueFormDto.getFile2());

            itemService.saveItem(VueFormDto.makeItemFormDto(vueFormDto),itemImgFileList);
        }catch (Exception e){
            //model.addAttribute("errorMessage","상품 등록 중 에러가 발생하였습니다.");
            return "서버 저장중 에러 발생";
        }
        //itemServiec.saveItem(itemFormDto, image);
        System.out.println("================= 저장 성공");
        return "저장 성공";
    }
}
