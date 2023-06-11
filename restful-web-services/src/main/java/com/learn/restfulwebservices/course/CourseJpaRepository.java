package com.learn.restfulwebservices.course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CourseJpaRepository {

    @PersistenceContext // or you can use `@Autowired`
    private EntityManager entityManager;

    public void insert(Course course) {
        entityManager.merge(course);
    }

    public Course findById(int id) {
        return entityManager.find(Course.class, id);
    }

    public void deleteById(int id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }
}
