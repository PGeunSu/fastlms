package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.course.dto.CourseDto;
import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.entity.Course;
import com.zerobase.fastlms.course.entity.TakeCourse;
import com.zerobase.fastlms.course.entity.TakeCourseCode;
import com.zerobase.fastlms.course.mapper.CourseMapper;
import com.zerobase.fastlms.course.mapper.TakeCourseMapper;
import com.zerobase.fastlms.course.model.CourseInput;
import com.zerobase.fastlms.course.model.CourseParam;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.model.TakeCourseInput;
import com.zerobase.fastlms.course.model.TakeCourseParam;
import com.zerobase.fastlms.course.repository.CourseRepository;
import com.zerobase.fastlms.course.repository.TakeCourseRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class TakeCourseServiceImpl implements TakeCourseService{

  private final TakeCourseMapper takeCourseMapper;
  private final TakeCourseRepository takeCourseRepository;


  @Override
  public List<TakeCourseDto> list(TakeCourseParam parameter) {

    long totalCount = takeCourseMapper.selectListCount(parameter);
    List<TakeCourseDto> list = takeCourseMapper.selectList(parameter);

    if (!CollectionUtils.isEmpty(list)){
      int i = 0;
      for (TakeCourseDto x : list){
        x.setTotalCount(totalCount);
        x.setSeq(totalCount - parameter.getPageStart() - i);
        i++;
      }
    }
    return list;

  }

  @Override
  public ServiceResult updateStatus(long id, String status) {

    Optional<TakeCourse> optionalTakeCourse = takeCourseRepository.findById(id);
    if (!optionalTakeCourse.isPresent()){
      return new ServiceResult(false, "수강 정보가 존재하지 않습니다.");
    }

    TakeCourse takeCourseMapper = optionalTakeCourse.get();

    takeCourseMapper.setStatus(status);
    takeCourseRepository.save(takeCourseMapper);

    return new ServiceResult(true);
  }
}
