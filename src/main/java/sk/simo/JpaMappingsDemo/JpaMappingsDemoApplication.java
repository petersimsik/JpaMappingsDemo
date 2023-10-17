package sk.simo.JpaMappingsDemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sk.simo.JpaMappingsDemo.dao.InstructorDao;
import sk.simo.JpaMappingsDemo.dao.InstructorDetailDao;
import sk.simo.JpaMappingsDemo.entity.Instructor;
import sk.simo.JpaMappingsDemo.entity.InstructorDetail;

@SpringBootApplication
public class JpaMappingsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaMappingsDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(InstructorDao instructorDao, InstructorDetailDao instructorDetailDao) {
		return runner -> {
			System.out.println("Application started");
			//createInstructor(instructorDao);
			//findInstructor(instructorDao);
			//deleteInstructorById(instructorDao);
			//findInstructorDetail(instructorDetailDao);
			deleteInstructorDetailById(instructorDetailDao);

			System.out.println("Application end.");
		};
	}

	private void deleteInstructorDetailById(InstructorDetailDao instructorDetailDao) {
		instructorDetailDao.deleteById(9);
	}

	private void findInstructorDetail(InstructorDetailDao instructorDetailDao) {
		InstructorDetail instructorDetail = instructorDetailDao.findById(6);
		System.out.println(instructorDetail);
		System.out.println(instructorDetail.getInstructor());
	}

	private void deleteInstructorById(InstructorDao instructorDao) {
		instructorDao.deleteInstructorById(2);
	}

	private void findInstructor(InstructorDao instructorDao) {
		Instructor instructor = instructorDao.findInstructorById(1);
		System.out.println("Instructor: " + instructor);
	}

	private void createInstructor(InstructorDao instructorDao) {
		/*
		Instructor instructor = new Instructor("Peter", "Simsik", "peter.simsik@gmail.com");
		InstructorDetail detail = new InstructorDetail("youtube.com/simo", "coding");
		 */
		Instructor instructor = new Instructor("Janko", "Mrkvicka", "janko.mrkvicka@gmail.com");
		InstructorDetail detail = new InstructorDetail("youtube.com/janko", "surfing");
		instructor.setInstructorDetail(detail);
		System.out.println("Saving instructor: " + instructor);
		instructorDao.save(instructor);
	}

}
