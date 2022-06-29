package com.shop.repository;

import com.shop.dto.BlogDto;
import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.entity.Item;

import java.util.List;

public interface BlogRepositoryCustom{

    List<BlogDto> getMainBlogPage();

}