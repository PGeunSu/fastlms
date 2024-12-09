package com.zerobase.fastlms.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member implements MemberCode {

  @Id
  private String userId;

  private String userName;
  private String phone;
  private String password;
  private LocalDateTime regDt;

  private boolean emailAuthYn;

  private LocalDateTime emailAuthDt;
  private String emailAuthKey;

  private String resetPasswordKey;
  private LocalDateTime resetPasswordLimitDt;

  private boolean adminYn;

  private String userStatus;

}
