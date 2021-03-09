package com.siyi.service.solo;

import com.siyi.entity.bo.ShopCategory;
import com.siyi.entity.dto.Result;

import java.util.List;

public interface ShopCategoryService {
    public Result<Boolean> addShopCategory(ShopCategory shopCategory);
    public Result<Boolean> removeShopCategory(int shopCategoryId);
    public Result<Boolean> modifyShopCategory(ShopCategory shopCategory);
    public Result<ShopCategory> queryShopCategoryById(int shopCategoryId);
    public Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition,int pageInde,int pageSize);
}
