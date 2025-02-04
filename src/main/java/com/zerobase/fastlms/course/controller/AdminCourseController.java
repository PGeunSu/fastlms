package com.zerobase.fastlms.course.controller;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.admin.service.CategoryService;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.service.CourseService;
import com.zerobase.fastlms.util.PagingUtil;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminCourseController extends BaseController {

  private final CourseService courseService;
  private final CategoryService categoryService;

  @GetMapping("/admin/course/list.do")
  public String list(Model model, CourseParam parameter){
    parameter.init();

    List<CourseDto> course = courseService.list(parameter);

    long totalCount = 0;
    if (!CollectionUtils.isEmpty(course)){
      totalCount = course.get(0).getTotalCount();
    }
    String queryString = parameter.getQueryString();

    String pagerHtml = getPagerHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

    model.addAttribute("pager", pagerHtml);
    model.addAttribute("list", course);
    model.addAttribute("totalCount", totalCount);


    return "admin/course/list";
  }


  @GetMapping(value = {"/admin/course/add.do", "/admin/course/edit.do"} )
  public String add(Model model, HttpServletRequest request, CourseInput parameter){

    model.addAttribute("category",  categoryService.list());

    boolean editMode = request.getRequestURI().contains("edit.do");
    CourseDto detail = new CourseDto();

    if (editMode){
      long id = parameter.getId();
      CourseDto existCourse = courseService.getById(id);

      if (existCourse == null){
        //error 처리
        model.addAttribute("message","강좌정보가 존재하지 않습니다");
        return "error/denied";
      }

      detail = existCourse;
    }

    model.addAttribute("detail", detail);
    model.addAttribute("editMode", editMode);
    return "admin/course/add";
  }

  @PostMapping(value = {"/admin/course/add.do", "/admin/course/edit.do"})
  public String addSubmit(Model model, HttpServletRequest request, CourseInput parameter){


    boolean editMode = request.getRequestURI().contains("edit.do");

    if (editMode){
      long id = parameter.getId();
      CourseDto existCourse = courseService.getById(id);

      if (existCourse == null){
        //error 처리
        model.addAttribute("message","강좌정보가 존재하지 않습니다");
        return "error/denied";
      }

      boolean result = courseService.set(parameter);

    }else{
      boolean result = courseService.add(parameter);
    }

    return "redirect:/admin/course/list.do";
  }

  @PostMapping("/admin/course/delete.do")
  public String delete(Model model, CourseInput parameter){

    boolean result = courseService.del(parameter.getIdList());


    return "redirect:/admin/course/list.do";
  }



}
