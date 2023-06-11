package com.learn.restfulwebservices.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {

    @Autowired
    private JdbcTemplate springJdbcTemplate;

    private static final String INSERT_QUERY =
            """
            insert into course(id, name, author) values (?, ?, ?);
            """;

    private static final String DELETE_QUERY =
            """
            delete from course where id = ?;
            """;

    private static final String SELECT_QUERY =
            """
            select * from course where id = ?;
            """;

    public void insert(Course course) {
        this.springJdbcTemplate.update(INSERT_QUERY,
                course.getId(), course.getName(), course.getAuthor());
    }

    public void deleteById(int id) {
        this.springJdbcTemplate.update(DELETE_QUERY, id);
    }

    public Course findById(int id) {
        // ResultSet -> Bean : Row Mapper
        return this.springJdbcTemplate.queryForObject(SELECT_QUERY,
                new BeanPropertyRowMapper<>(Course.class), id);
    }
}
