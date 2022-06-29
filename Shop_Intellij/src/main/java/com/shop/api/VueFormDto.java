package com.shop.api;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemFormDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VueFormDto {
    private String itemNm;
    private int price;
    private int stockNumber;
    private String itemDetail;
    private MultipartFile file1;
    private MultipartFile file2;

    public static ItemFormDto makeItemFormDto(VueFormDto vueFormDto){
        ItemFormDto itemFormDto = new ItemFormDto();
        itemFormDto.setItemNm(vueFormDto.getItemNm());
        itemFormDto.setPrice(vueFormDto.getPrice());
        itemFormDto.setStockNumber(vueFormDto.getStockNumber());
        itemFormDto.setItemDetail(vueFormDto.getItemDetail());
        itemFormDto.setItemSellStatus(ItemSellStatus.SELL);
        return itemFormDto;
    }

}
