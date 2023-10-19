package sk.simo.JpaMappingsDemo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import sk.simo.JpaMappingsDemo.entity.Course;

import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {

    private EntityManager em;

    public CourseDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Course> getCoursesByInstructorId(long instructorId) {
        TypedQuery<Course> query = em.createQuery("from Course WHERE instructor.id = :instructorId", Course.class);
        query.setParameter("instructorId", instructorId);
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Course getCourseById(long id) {
        return em.find(Course.class, id);
    }

    @Override
    @Transactional
    public void update(Course course) {
        em.merge(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(long id) {
        Course course = getCourseById(id);
        em.remove(course);
    }
}
