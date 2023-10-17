package sk.simo.JpaMappingsDemo.dao;

import sk.simo.JpaMappingsDemo.entity.InstructorDetail;

public interface InstructorDetailDao {
    InstructorDetail findById(long id);
    void deleteById(long id);
}
