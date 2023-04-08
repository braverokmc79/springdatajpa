package com.jpa.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.transaction.annotation.Transactional;

//@Component
@Transactional
public class 핵심개념이해정리14 implements ApplicationRunner {

    @Autowired
    PostRepository postRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Post post =new Post();
        post.setTitle("spring");

        Comment comment =new Comment();
        comment.setComment("hello");

        postRepository.save(post);
    }
}
