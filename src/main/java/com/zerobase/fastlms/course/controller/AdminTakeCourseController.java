package com.zerobase.fastlms.course.controller;

import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.model.TakeCourseParam;
import com.zerobase.fastlms.course.service.TakeCourseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminTakeCourseController extends BaseController {

  private final TakeCourseService takeCourseService;

  @GetMapping("/admin/takeCourse/list.do")
  public String list(Model model, TakeCourseParam parameter){
    parameter.init();

    List<TakeCourseDto> course = takeCourseService.list(parameter);

    long totalCount = 0;
    if (!CollectionUtils.isEmpty(course)){
      totalCount = course.get(0).getTotalCount();
    }
    String queryString = parameter.getQueryString();

    String pagerHtml = getPagerHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

    model.addAttribute("pager", pagerHtml);
    model.addAttribute("list", course);
    model.addAttribute("totalCount", totalCount);


    return "admin/takeCourse/list";
  }

  @PostMapping("/admin/takeCourse/status.do")
  public String updateStatus(Model model, TakeCourseParam parameter){

    ServiceResult result = takeCourseService.updateStatus(parameter.getId(), parameter.getStatus());

    if (!result.isResult()){
      model.addAttribute("message", result.getMessage());
      return "error/denied";
    }

    return "redirect:/admin/takeCourse/list.do";
  }


}
