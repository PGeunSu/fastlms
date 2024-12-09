package com.zerobase.fastlms.admin.mapper;


import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import com.zerobase.fastlms.member.entity.Member;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

  List<MemberDto> selectList(MemberParam parameter);

  long selectListCount(MemberParam parameter);


}
