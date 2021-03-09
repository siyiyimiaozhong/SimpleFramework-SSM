package com.siyi.service.combine.impl;

import com.siyi.entity.bo.HeadLine;
import com.siyi.entity.bo.ShopCategory;
import com.siyi.entity.dto.MainPageInfoDTO;
import com.siyi.entity.dto.Result;
import com.siyi.service.combine.HeadLineShopCategoryCombineService;
import com.siyi.service.solo.HeadLineService;
import com.siyi.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.inject.annotation.Autowired;

import java.util.List;

@Service
public class HeadLineShopCategoryCombineServiceImpl implements HeadLineShopCategoryCombineService {

    @Autowired
    private HeadLineService headLineService;
    @Autowired
    private ShopCategoryService shopCategoryService;

    @Override
    public Result<MainPageInfoDTO> getMainPageInfo() {
        //1.获取头条列表
        HeadLine headLineCondition = new HeadLine();
        headLineCondition.setEnableStatus(1);
        Result<List<HeadLine>> headLineResult = headLineService.queryHeadLine(headLineCondition, 1, 4);
        //2.获取店铺类别列表
        ShopCategory shopCategoryCondition = new ShopCategory();
        Result<List<ShopCategory>> shopCategoryResult = shopCategoryService.queryShopCategory(shopCategoryCondition, 1, 100);
        //3.合并两者并返回
        Result<MainPageInfoDTO> result = mergeMainPageInfoResult(headLineResult,shopCategoryResult);
        return result;
    }

    private Result<MainPageInfoDTO> mergeMainPageInfoResult(Result<List<HeadLine>> headLineResult, Result<List<ShopCategory>> shopCategoryResult) {
        Result<MainPageInfoDTO> result = new Result<>();
        MainPageInfoDTO mainPageInfoDTO = new MainPageInfoDTO();
        mainPageInfoDTO.setHeadLineList(headLineResult.getData());
        mainPageInfoDTO.setShopCategoryList(shopCategoryResult.getData());
        result.setData(mainPageInfoDTO);
        result.setCode(200);
        return result;
    }
}
