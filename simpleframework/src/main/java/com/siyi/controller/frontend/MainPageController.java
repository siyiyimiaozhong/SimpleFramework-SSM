package com.siyi.controller.frontend;

import com.siyi.entity.dto.MainPageInfoDTO;
import com.siyi.entity.dto.Result;
import com.siyi.service.combine.HeadLineShopCategoryCombineService;
import lombok.Getter;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;
import org.simpleframework.mvc.annotation.RequestMapping;
import org.simpleframework.mvc.annotation.RequestParam;
import org.simpleframework.mvc.annotation.ResponseBody;
import org.simpleframework.mvc.type.ModelAndView;
import org.simpleframework.mvc.type.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Controller
@Getter
@RequestMapping("mainPage")
public class MainPageController {
    @Autowired
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;

    public Result<MainPageInfoDTO> getMainPageInfo(HttpServletRequest req, HttpServletResponse resp){
        return headLineShopCategoryCombineService.getMainPageInfo();
    }

    @RequestMapping(value = "fun", method = RequestMethod.GET)
    public void fun(){
        throw new RuntimeException("------------");
//        System.out.println("---fun---");
    }

    @RequestMapping(value = "returnJSON",method = RequestMethod.GET)
    @ResponseBody
    public List<String> returnJSON(){
        return Arrays.asList("123","234","345");
    }

    @RequestMapping(value = "m",method = RequestMethod.GET)
    public ModelAndView m(@RequestParam("name")String name,@RequestParam("age") Integer age){
        ModelAndView mv = new ModelAndView();
        mv.addViewData("name",name);
        mv.addViewData("age",age);
        mv.setView("hello.jsp");
        return mv;
    }
}
