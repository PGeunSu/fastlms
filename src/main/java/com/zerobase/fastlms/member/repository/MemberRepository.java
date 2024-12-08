package com.zerobase.fastlms.member.repository;

import com.zerobase.fastlms.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

  Optional<Member> findByEmailAuthKey(String emailAuthKey);

}