package com.zerobase.fastlms.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

  @RequestMapping("/")
  public String index(){

    return "index";
  }

  @RequestMapping("/error/denied")
  public String errorPage(){

    return "error/denied";
  }



}
