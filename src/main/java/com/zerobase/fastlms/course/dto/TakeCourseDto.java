package com.zerobase.fastlms.course.dto;

import com.zerobase.fastlms.course.entity.Course;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TakeCourseDto {

  long id;
  long categoryId;
  String userId;

  long payPrice;
  String status;
  LocalDateTime regDt;

  String userName;
  String phone;
  String subject;

  long totalCount;
  long seq;


  public String getRegDtText(){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

    return regDt != null ? regDt.format(formatter) : "";
  }

}
