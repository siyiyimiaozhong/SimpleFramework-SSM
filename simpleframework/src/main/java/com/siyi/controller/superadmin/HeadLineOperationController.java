package com.siyi.controller.superadmin;

import com.siyi.entity.bo.HeadLine;
import com.siyi.entity.dto.Result;
import com.siyi.service.solo.HeadLineService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class HeadLineOperationController {
    private HeadLineService headLineService;

    public Result<Boolean> addHeadLine(HttpServletRequest req, HttpServletResponse resp){
        //TODO 参数校验以及请求参数转化
        return headLineService.addHeadLine(new HeadLine());
    }

    public Result<Boolean> removeHeadLine(HttpServletRequest req, HttpServletResponse resp){
        //TODO 参数校验以及请求参数转化
        return headLineService.removeHeadLine(1);
    }

    public Result<Boolean> modifyHeadLine(HttpServletRequest req, HttpServletResponse resp){
        //TODO 参数校验以及请求参数转化
        return headLineService.modifyHeadLine(new HeadLine());
    }

    public Result<HeadLine> queryHeadLineById(HttpServletRequest req, HttpServletResponse resp){
        //TODO 参数校验以及请求参数转化
        return headLineService.queryHeadLineById(1);
    }

    public Result<List<HeadLine>> queryHeadLine(HttpServletRequest req, HttpServletResponse resp){
        //TODO 参数校验以及请求参数转化
        return headLineService.queryHeadLine(null,1,100);
    }
}
