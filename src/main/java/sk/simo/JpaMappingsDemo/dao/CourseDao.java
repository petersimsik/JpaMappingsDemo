package sk.simo.JpaMappingsDemo.dao;

import sk.simo.JpaMappingsDemo.entity.Course;

import java.util.List;

public interface CourseDao {
    List<Course> getCoursesByInstructorId(long instructorId);
    Course getCourseById(long id);
    void update(Course course);
    void deleteCourseById(long id);
    void save(Course course);
    Course findCourseByIdWithReviews(long id);
}
