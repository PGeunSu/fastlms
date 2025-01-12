package com.zerobase.fastlms.course.mapper;


import com.zerobase.fastlms.admin.dto.CategoryDto;
import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.model.CourseParam;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

  List<CategoryDto> select(CategoryDto parameter);


}
