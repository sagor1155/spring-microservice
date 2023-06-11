package com.learn.restfulwebservices.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CourseJpaCommandLineRunner implements CommandLineRunner {

    @Autowired
    private CourseJpaRepository repository;
    @Override
    public void run(String... args) throws Exception {
        repository.insert(new Course(1, "Spring Jdbc", "Lee Luise"));
        repository.insert(new Course(2, "Spring Jdbc", "Lee Luise"));
        repository.insert(new Course(3, "Spring Jdbc", "Lee Luise"));

        repository.deleteById(2);

        System.out.println(repository.findById(1));
        System.out.println(repository.findById(3));
    }
}
