package com.zerobase.fastlms.member.controller;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import com.zerobase.fastlms.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @RequestMapping("/member/login")
  public String login() {

    return "member/login";
  }

  @GetMapping("/member/find/password")
  public String findPassword() {

    return "member/find_password";
  }

  @PostMapping("/member/find/password")
  public String findPasswordSubmit(Model model, ResetPasswordInput parameter) {

    boolean result = memberService.sendRequestPassword(parameter);

    model.addAttribute("result", result);

    return "member/find_password_result";
  }


  @GetMapping("/member/register")
  public String register() {

    return "member/register";
  }


  @PostMapping("/member/register")
  public String registerSubmit(Model model, HttpServletRequest request, MemberInput parameter) {

    boolean result = memberService.register(parameter);

    model.addAttribute("result", result);

    return "member/register_complete";
  }

  @GetMapping("/member/email-auth")
  public String emailAuth(Model model, HttpServletRequest request) {
    String uuid = request.getParameter("id");
    System.out.println(uuid);

    boolean result = memberService.emailAuth(uuid);
    model.addAttribute("result", result);

    return "member/email_auth";
  }

  @GetMapping("/member/info")
  public String memberInfo(Model model, Principal principal) {

    String userId = principal.getName();
    MemberDto detail = memberService.detail(userId);

    model.addAttribute("detail", detail);

    return "member/member_info";
  }

  @PostMapping("/member/info")
  public String memberInfo(Model model, MemberInput parameter, Principal principal) {

    String userId = principal.getName();
    parameter.setUserId(userId);

    ServiceResult result = memberService.updateMember(parameter);

    if (!result.isResult()) {
      model.addAttribute("message", result.getMessage());
      return "error/denied";
    }


    return "redirect:/member/info";
  }

  @GetMapping("/member/password")
  public String modifyPassword(Model model, Principal principal) {

    String userId = principal.getName();
    MemberDto detail = memberService.detail(userId);

    model.addAttribute("detail", detail);

    return "member/password";
  }

  @PostMapping("/member/password")
  public String modifyPassword(Model model, MemberInput parameter, Principal principal) {

    String userId = principal.getName();
    parameter.setUserId(userId);

    ServiceResult result = memberService.updateMemberPassword(parameter);

    if (!result.isResult()) {
      model.addAttribute("message", result.getMessage());
      return "error/denied";
    }

    return "redirect:/member/info";
  }

  @GetMapping("/member/takeCourse")
  public String memberTakeCourse(Model model, Principal principal) {

    return "member/takeCourse";
  }

  @GetMapping("/member/reset/password")
  public String resetPassword(Model model, HttpServletRequest request) {

    String uuid = request.getParameter("id");

    boolean result = memberService.checkResetPassword(uuid);

    model.addAttribute("result", result);

    return "member/reset_password";
  }

  @PostMapping("/member/reset/password")
  public String resetPasswordSubmit(Model model, ResetPasswordInput parameter) {

    boolean result = memberService.resetPassword(parameter.getId(), parameter.getPassword());

    model.addAttribute("result", result);

    return "member/reset_password_result";

  }


}
