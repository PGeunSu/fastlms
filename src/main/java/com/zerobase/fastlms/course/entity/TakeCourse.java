package com.zerobase.fastlms.course.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TakeCourse implements TakeCourseCode {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  long courseId;
  String userId;

  long payPrice;
  String status;

  LocalDateTime regDt;

}
