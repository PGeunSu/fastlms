package com.zerobase.fastlms.course.dto;

import com.zerobase.fastlms.course.entity.Course;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class CourseDto {

  Long id;
  long categoryId;

  String imagePath;
  String keyword;
  String subject;
  String summary;
  String contents;
  long price;
  long salePrice;
  LocalDate saleEndDt;
  LocalDateTime regDt;
  LocalDateTime udtDt;

  long totalCount;
  long seq;

  public static CourseDto of(Course course) {
    return CourseDto.builder()
        .id(course.getId())
        .categoryId(course.getCategoryId())
        .imagePath(course.getImagePath())
        .keyword(course.getKeyword())
        .subject(course.getSubject())
        .summary(course.getSummary())
        .contents(course.getContents())
        .price(course.getPrice())
        .salePrice(course.getSalePrice())
        .saleEndDt(course.getSaleEndDt())
        .regDt(course.getRegDt())
        .udtDt(course.getUdtDt())
        .build();
  }


  public static List<CourseDto> of(List<Course> courses) {

    if (courses == null) {
      return null;
    }

    List<CourseDto> courseList = new ArrayList<>();
    for (Course x : courses) {
      courseList.add(CourseDto.of(x));
    }
    return courseList;

  }

}
