package com.zerobase.fastlms.admin.controller;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberInput;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.course.controller.BaseController;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.service.MemberService;
import com.zerobase.fastlms.util.PagingUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminMemberController extends BaseController {

  private final MemberService memberService;

  @GetMapping("/admin/member/list.do")
  public String list(Model model, MemberParam parameter){

    parameter.init();

    List<MemberDto> members = memberService.list(parameter);

    long totalCount = 0;
    if (members != null && members.size() > 0){
      totalCount = members.get(0).getTotalCount();
    }
    String queryString = parameter.getQueryString();

    String pagerHtml = getPagerHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

    model.addAttribute("pager", pagerHtml);
    model.addAttribute("list", members);
    model.addAttribute("totalCount", totalCount);

    return "admin/member/list";
  }

  @GetMapping("/admin/member/detail.do")
  public String detail(Model model, MemberParam parameter){

    parameter.init();

    MemberDto member = memberService.detail(parameter.getUserId());
    model.addAttribute("member", member);

    return "admin/member/detail";
  }

  @PostMapping("/admin/member/status.do")
  public String status(MemberInput parameter){

    boolean result = memberService.updateStatus(parameter.getUserId(), parameter.getUserStatus());

    return "redirect:/admin/member/detail.do?userId=" + parameter.getUserId();
  }
  @PostMapping("/admin/member/password.do")
  public String password(MemberInput parameter){

    boolean result = memberService.updatePassword(parameter.getUserId(), parameter.getPassword());

    return "redirect:/admin/member/detail.do?userId=" + parameter.getUserId();
  }



}
