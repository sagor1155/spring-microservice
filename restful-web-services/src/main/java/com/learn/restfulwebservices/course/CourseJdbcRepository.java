package com.learn.restfulwebservices.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {

    @Autowired
    private JdbcTemplate springJdbcTemplate;

    private static final String INSERT_QUERY =
            """
            insert into course(id, name, author) values (1, 'Spring Jpa', 'Lee Luise');
            """;

    public void insert() {
        this.springJdbcTemplate.update(INSERT_QUERY);
    }
}
