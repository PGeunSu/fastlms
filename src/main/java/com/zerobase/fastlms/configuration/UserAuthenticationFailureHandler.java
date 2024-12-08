package com.zerobase.fastlms.configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class UserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {

    String msg = "로그인 실패";

    if (exception instanceof InternalAuthenticationServiceException){
      msg = exception.getMessage();
    }

    setUseForward(true);
    setDefaultFailureUrl("/member/login?error=ture");
    request.setAttribute("errorMessage", msg);


    super.onAuthenticationFailure(request, response, exception);
  }
}
