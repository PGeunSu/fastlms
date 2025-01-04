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
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  long categoryId;

  String imagePath;
  String keyword;
  String subject;

  @Column(length = 100)
  String summary;
  @Lob
  String contents;

  long price;
  long salePrice;

  LocalDate saleEndDt;
  LocalDateTime regDt;
  LocalDateTime udtDt;

}
