package com.zerobase.fastlms.course.mapper;


import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.model.TakeCourseParam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TakeCourseMapper {

  List<TakeCourseDto> selectList(TakeCourseParam parameter);

  long selectListCount(TakeCourseParam parameter);


}
