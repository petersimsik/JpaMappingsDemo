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

    @Override
    @Transactional
    public void save(Course course) {
        em.persist(course);
    }

    @Override
    public Course findCourseByIdWithReviews(long id) {
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c JOIN FETCH c.reviews WHERE c.id = :id", Course.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Course findCourseByIdWithStudents(long id) {
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c JOIN FETCH c.students WHERE c.id = :courseId", Course.class);
        query.setParameter("courseId", id);
        return query.getSingleResult();
    }
}
