package com.zerobase.fastlms.member.service.impl;

import com.zerobase.fastlms.components.MailComponents;
import com.zerobase.fastlms.member.entity.Member;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.repository.MemberRepository;
import com.zerobase.fastlms.member.service.MemberService;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;
  private final MailComponents mailComponents;

  @Override
  public boolean register(MemberInput parameter) {

    Optional<Member> optionalMember = memberRepository.findById(parameter.getUserId());
    if (optionalMember.isPresent()){
      //현재 userId에 해당하는 데이터 존재
      return false;
    }

    String uuid = UUID.randomUUID().toString();

    Member member = Member.builder()
        .userId(parameter.getUserId())
        .userName(parameter.getUserName())
        .password(parameter.getPassword())
        .phone(parameter.getPhone())
        .regDt(LocalDateTime.now())
        .emailAuthYn(false)
        .emailAuthKey(uuid)
        .build();

    memberRepository.save(member);

    String email = parameter.getUserId();
    String subject = "fastlms 가입 확인";
    String text = "<p>사이트 가입이 완료 되었습니다</p> <p>아래 링크를 클릭하여 가입을 완료하세요.</p>"
        + "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id="+uuid+"'>이메일 인증</a></div>";

    mailComponents.sendMail(email,subject,text);

    return true;
  }

  @Override
  public boolean emailAuth(String uuid) {


    Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(uuid);
    if (!optionalMember.isPresent()){
      return false;
    }

    Member member = optionalMember.get();
    member.setEmailAuthYn(true);
    member.setEmailAuthDt(LocalDateTime.now());
    memberRepository.save(member);

    return true;
  }
}
