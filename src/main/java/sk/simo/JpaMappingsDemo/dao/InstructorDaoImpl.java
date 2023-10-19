package sk.simo.JpaMappingsDemo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import sk.simo.JpaMappingsDemo.entity.Course;
import sk.simo.JpaMappingsDemo.entity.Instructor;

import java.util.List;

@Repository
public class InstructorDaoImpl implements InstructorDao {

    private EntityManager em;

    public InstructorDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        em.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(long id) {
        Instructor instructor = em.find(Instructor.class, id);
        return instructor;
    }

    @Override
    @Transactional
    public void deleteInstructorById(long id) {
        Instructor instructor = findInstructorById(id);
        if (instructor != null)
        {
            List<Course> courses = instructor.getCourses();
            courses.forEach(course -> course.setInstructor(null));
            em.remove(instructor);
        }
    }

    @Override
    public Instructor findInstructorByIdWithCourses(long id) {
        TypedQuery<Instructor> query = em.createQuery("SELECT i FROM Instructor i " +
                        "  JOIN FETCH i.courses " +
                        "  JOIN FETCH i.instructorDetail " +
                        "  WHERE i.id = :instructorId"
                , Instructor.class);
        query.setParameter("instructorId", id);
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        em.merge(instructor);
    }
}
