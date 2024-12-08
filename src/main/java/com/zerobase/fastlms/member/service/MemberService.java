package com.zerobase.fastlms.member.service;

import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface MemberService extends UserDetailsService {

  boolean register(MemberInput parameter);

  boolean emailAuth(String uuid);

  boolean sendRequestPassword(ResetPasswordInput parameter);

  boolean resetPassword(String uuid, String password);

  boolean checkResetPassword(String uuid);
}
