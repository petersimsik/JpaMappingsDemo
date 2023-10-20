package sk.simo.JpaMappingsDemo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import sk.simo.JpaMappingsDemo.entity.Student;

@Repository
public class StudentDaoImpl implements StudentDao {
    private EntityManager em;

    public StudentDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Student findStudentByIdWithCourses(long id) {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s JOIN FETCH s.courses WHERE s.id = :studentId", Student.class);
        query.setParameter("studentId", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student student) {
        em.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(long id) {
        Student student = em.find(Student.class, id);
        em.remove(student);
    }
}
