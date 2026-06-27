package com.Ankita.SecurityApplication.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

  private Long postId;
  private String title;
  private String description;

}
