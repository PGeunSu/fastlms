package com.zerobase.fastlms;

import com.zerobase.fastlms.components.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

  @RequestMapping("/")
  public String index(){

//    String email = "pks4877@naver.com";
//    String subject = "제로베이스";
//    String text = "<p>안녕하세요.</p><p>ㅎㅇ</p>";
//
//    mailComponents.sendMail(email,subject,text);

    return "index";
  }

}
