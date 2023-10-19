package sk.simo.JpaMappingsDemo;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sk.simo.JpaMappingsDemo.dao.CourseDao;
import sk.simo.JpaMappingsDemo.dao.InstructorDao;
import sk.simo.JpaMappingsDemo.dao.InstructorDetailDao;
import sk.simo.JpaMappingsDemo.entity.Course;
import sk.simo.JpaMappingsDemo.entity.Instructor;
import sk.simo.JpaMappingsDemo.entity.InstructorDetail;
import sk.simo.JpaMappingsDemo.entity.Review;

import java.util.List;

@SpringBootApplication
public class JpaMappingsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaMappingsDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(InstructorDao instructorDao, InstructorDetailDao instructorDetailDao, CourseDao courseDao) {
		return runner -> {
			System.out.println("Application started");
			//createInstructor(instructorDao);

			//findInstructor(instructorDao);

			//deleteInstructorById(instructorDao);

			//findInstructorDetail(instructorDetailDao);

			//deleteInstructorDetailById(instructorDetailDao);

			//createInstructorWithCourses(instructorDao);

			//findInstructorWithCourses(instructorDao, courseDao);

			//findInstructorWithCoursesJoinFetch(instructorDao);

			//updateInstructor(instructorDao);

			//updateCourse(courseDao);

			//deleteCourseById(courseDao);

			//createCourseAndReviews(courseDao);

			findCourseWithReviews(courseDao);
			System.out.println("Application end.");
		};
	}

	private void findCourseWithReviews(CourseDao courseDao) {
		Course course = courseDao.findCourseByIdWithReviews(10);
		System.out.println(course);
		System.out.println("Reviews: " + course.getReviews());
	}

	private void createCourseAndReviews(CourseDao courseDao) {
		Course course = new Course("Scratch");
		course.addReview(new Review("Cool!"));
		course.addReview(new Review("Finally, the best of the best"));
		courseDao.save(course);
	}

	private void deleteCourseById(CourseDao courseDao) {
		courseDao.deleteCourseById(10);
	}

	private void updateCourse(CourseDao courseDao) {
		Course course = courseDao.getCourseById(10);
		course.setTitle("java is the best");
		courseDao.update(course);
	}

	private void updateInstructor(InstructorDao instructorDao) {
		Instructor instructor = instructorDao.findInstructorById(1);
		instructor.setLastName("TESTER");
		instructorDao.update(instructor);
	}

	private void findInstructorWithCourses(InstructorDao instructorDao, CourseDao courseDao) {
		//As fetch type is LAZY we normally get LazyInitializationException
		//there are two options
		//  a) create getCoursesByInstructorId
		//  b) use DTO projection
		//  c) use fetch join
		Instructor instructor = instructorDao.findInstructorById(1);
		System.out.println(instructor);
		List<Course> courses = courseDao.getCoursesByInstructorId(instructor.getId());
		System.out.println("Courses: " + courses);
	}

	private void findInstructorWithCoursesJoinFetch(InstructorDao instructorDao) {
		Instructor instructor = instructorDao.findInstructorByIdWithCourses(1);
		System.out.println(instructor);
		System.out.println("Courses: " + instructor.getCourses());
	}

	private void createInstructorWithCourses(InstructorDao instructorDao) {
		Instructor instructor = new Instructor("Peter", "Simsik", "peter.simsik@gmail.com");
		InstructorDetail detail = new InstructorDetail("youtube.com/simo", "coding");
		instructor.setInstructorDetail(detail);
		Course course1 = new Course("Java");
		Course course2 = new Course("Python");
		Course course3 = new Course("SQL");
		instructor.addCourse(course1);
		instructor.addCourse(course2);
		instructor.addCourse(course3);

		instructorDao.save(instructor);
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
		instructorDao.deleteInstructorById(1);
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
