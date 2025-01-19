package com.zerobase.fastlms.member.service;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.model.ResetPasswordInput;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {

  boolean register(MemberInput parameter);

  boolean emailAuth(String uuid);

  boolean sendRequestPassword(ResetPasswordInput parameter);

  boolean resetPassword(String uuid, String password);

  boolean checkResetPassword(String uuid);

  List<MemberDto> list(MemberParam parameter);

  MemberDto detail(String userId);

  boolean updateStatus(String userId, String userStatus);

  boolean updatePassword(String userId, String password);

  ServiceResult updateMemberPassword(MemberInput parameter);

  ServiceResult updateMember(MemberInput parameter);
}
