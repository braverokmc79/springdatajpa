package com.jpa.spring;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class PostDTO {

    private Long id;

    private String title;

    private Long commentId;
    private String comment;

}
