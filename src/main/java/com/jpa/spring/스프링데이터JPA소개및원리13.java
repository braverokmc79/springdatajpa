package com.jpa.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//@Component
@Transactional
public class 스프링데이터JPA소개및원리13  implements ApplicationRunner {

	@Autowired
	PostRepository postRepository;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("=================================================");
		postRepository.findAll().forEach(System.out::println);
		System.out.println("=================================================");
	}

}
