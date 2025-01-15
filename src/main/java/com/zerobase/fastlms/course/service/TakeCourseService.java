package com.zerobase.fastlms.course.service;

import com.zerobase.fastlms.course.dto.TakeCourseDto;
import com.zerobase.fastlms.course.model.ServiceResult;
import com.zerobase.fastlms.course.model.TakeCourseParam;
import java.util.List;

public interface TakeCourseService {

  List<TakeCourseDto> list(TakeCourseParam parameter);

  ServiceResult updateStatus(long id, String status);

}
