package sk.simo.JpaMappingsDemo.dao;

import sk.simo.JpaMappingsDemo.entity.Instructor;

public interface InstructorDao {
    void save(Instructor instructor);
    Instructor findInstructorById(long id);
    void deleteInstructorById(long id);
}
