package sk.simo.JpaMappingsDemo.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import sk.simo.JpaMappingsDemo.entity.InstructorDetail;

@Repository
public class InstructorDetailDaoImpl implements InstructorDetailDao{

    private EntityManager em;

    public InstructorDetailDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public InstructorDetail findById(long id) {
        return em.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        InstructorDetail instructorDetail = findById(id);
        if (instructorDetail != null) {
            instructorDetail.getInstructor().setInstructorDetail(null);
            em.remove(instructorDetail);
        }
    }
}
