package mk.ukim.finki.wp.kol2022.g2.repository;

import mk.ukim.finki.wp.kol2022.g2.model.Course;
import mk.ukim.finki.wp.kol2022.g2.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    public List<Student> findAllByCoursesContainingAndEnrollmentDateBetween(Course course, LocalDate date1, LocalDate date2);
    public List<Student> findAllByCoursesContainingAndEnrollmentDateAfterAndEnrollmentDateBefore(Course course, LocalDate date1, LocalDate date2);
    public List<Student> findAllByCoursesContainingAndEnrollmentDateAfter(Course course, LocalDate date);
    public List<Student> findAllByCoursesContainingAndEnrollmentDateBefore(Course course, LocalDate date);

    public List<Student> findAllByCoursesContaining (Course course);
    public List<Student> findAllByEnrollmentDateBetween (LocalDate date1, LocalDate date2);

    public Student findByEmail(String email);
}
