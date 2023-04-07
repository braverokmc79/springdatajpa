package com.jpa.spring;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(of = {"id", "title"})
public class Post {

    @Id
    @GeneratedValue
    @Column(name="post_id")
    private Long id;

    private String title;

    //CascadeType.ALL 는 comment
    //    persist(commentA);
    //    persist(commentB);
    //    persist(commentB);
    //    persist(commentB);
    //    persist(post);
    // =>persist를 각각 해줘야 하는데 CascadeType.ALL  적용하면  persist(study); 한번에 적용된다.
    @OneToMany(mappedBy = "post",  cascade = CascadeType.ALL)
    private List<Comment> comments =new ArrayList<>();

    /** Set 으로 할경우  hashCode 에 대한 StackOverflowError 나와서 id 에 대한 hashCode 를 설정해 줘야 한다.
    // StackOverflowError hashCode
     **/
    public void addComment(Comment comment){
        this.getComments().add(comment);
        comment.setPost(this);
    }


}
