package sk.simo.JpaMappingsDemo.dao;

import sk.simo.JpaMappingsDemo.entity.Student;

public interface StudentDao {
    Student findStudentByIdWithCourses(long id);
    void update(Student student);
    void deleteStudentById(long id);
}
