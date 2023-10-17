package sk.simo.JpaMappingsDemo.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import sk.simo.JpaMappingsDemo.entity.Instructor;

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
            em.remove(instructor);
    }

}
