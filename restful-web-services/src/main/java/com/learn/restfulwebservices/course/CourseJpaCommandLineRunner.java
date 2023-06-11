package com.learn.restfulwebservices.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseJpaCommandLineRunner implements CommandLineRunner {

//    @Autowired
//    private CourseJdbcRepository repository;

//    @Autowired
//    private CourseJpaRepository repository;

    @Autowired
    private CourseSpringDataJpaRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Course(1L, "Spring Jdbc", "Lee Luise"));
        repository.save(new Course(2L, "Spring Jdbc", "Lee Luise"));
        repository.save(new Course(3L, "Spring Jdbc", "Lee Luise"));

        repository.deleteById(2L);

        System.out.println(repository.findById(1L));
        System.out.println(repository.findById(3L));

        System.out.println(repository.findByAuthor("Lee Luise"));

    }
}
