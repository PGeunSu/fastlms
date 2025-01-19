package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.member.entity.Member;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

  private String userId;

  private String userName;
  private String phone;
  private String password;

  private LocalDateTime regDt;
  private LocalDateTime udtDt;

  private boolean emailAuthYn;
  private LocalDateTime emailAuthDt;
  private String emailAuthKey;

  private String resetPasswordKey;
  private LocalDateTime resetPasswordLimitDt;

  private boolean adminYn;
  String userStatus;

  private String zipCode;
  private String addr;
  private String addrDetail;

  long totalCount;
  long seq;

  public static MemberDto of(Member member){
    return MemberDto.builder()
        .userId(member.getUserId())
        .userName(member.getUserName())
        .phone(member.getPhone())
        .regDt(member.getRegDt())
        .udtDt(member.getUdtDt())
        .emailAuthYn(member.isEmailAuthYn())
        .emailAuthDt(member.getEmailAuthDt())
        .emailAuthKey(member.getEmailAuthKey())
        .resetPasswordKey(member.getResetPasswordKey())
        .resetPasswordLimitDt(member.getResetPasswordLimitDt())
        .adminYn(member.isAdminYn())
        .userStatus(member.getUserStatus())
        .zipCode(member.getZipCode())
        .addr(member.getAddr())
        .addrDetail((member.getAddrDetail()))
        .build();

  }

  public String getRegDtText(){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

    return regDt != null ? regDt.format(formatter) : "";
  }
  public String getUdtDtText(){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

    return udtDt != null ? udtDt.format(formatter) : "";
  }


}
