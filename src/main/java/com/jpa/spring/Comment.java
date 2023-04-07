package com.jpa.spring;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue
    @Column(name="comment_id")
    private Long id;

    private String comment;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;


}
