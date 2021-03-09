package com.siyi.entity.dto;

import com.siyi.entity.bo.HeadLine;
import com.siyi.entity.bo.ShopCategory;
import lombok.Data;

import java.util.List;

@Data
public class MainPageInfoDTO {
    private List<HeadLine> headLineList;
    private List<ShopCategory> shopCategoryList;
}
