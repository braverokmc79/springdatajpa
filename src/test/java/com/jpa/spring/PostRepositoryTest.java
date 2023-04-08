package com.jpa.spring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;


    @Test
    public void crudRepository(){
        //Given
        Post post =new Post();
        post.setTitle("hello spring boot common");
        Assertions.assertThat(post.getId()).isNull();

        //when
        Post newPost=postRepository.save(post);

        //Then
        Assertions.assertThat(newPost.getId()).isNotNull();

        //When
        List<Post> posts =postRepository.findAll();

        Assertions.assertThat(posts.size()).isEqualTo(1);
        Assertions.assertThat(posts).contains(newPost);


         //when
        Page<Post> page = postRepository.findAll(PageRequest.of(0, 10));
        Assertions.assertThat(page.getTotalElements()).isEqualTo(1);
        Assertions.assertThat(page.getNumber()).isEqualTo(0);
        Assertions.assertThat(page.getSize()).isEqualTo(10);
        Assertions.assertThat(page.getNumberOfElements()).isEqualTo(1);

        //when
        page= postRepository.findByTitleContains("spring", PageRequest.of(0, 10));
        Assertions.assertThat(page.getTotalElements()).isEqualTo(1);
        Assertions.assertThat(page.getNumber()).isEqualTo(0);
        Assertions.assertThat(page.getSize()).isEqualTo(10);
        Assertions.assertThat(page.getNumberOfElements()).isEqualTo(1);

        //when
        long spring=postRepository.countByTitleContains("spring");

        //Then

    }




}