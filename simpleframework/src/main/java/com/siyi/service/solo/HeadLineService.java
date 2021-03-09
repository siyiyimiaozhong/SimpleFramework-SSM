package com.siyi.service.solo;

import com.siyi.entity.bo.HeadLine;
import com.siyi.entity.dto.Result;

import java.util.List;

public interface HeadLineService {
    public Result<Boolean> addHeadLine(HeadLine headLine);
    public Result<Boolean> removeHeadLine(int headLineId);
    public Result<Boolean> modifyHeadLine(HeadLine headLine);
    public Result<HeadLine> queryHeadLineById(int HeadLineId);
    public Result<List<HeadLine>> queryHeadLine(HeadLine headLineCondition,int pageIndex,int pageSize);
}
