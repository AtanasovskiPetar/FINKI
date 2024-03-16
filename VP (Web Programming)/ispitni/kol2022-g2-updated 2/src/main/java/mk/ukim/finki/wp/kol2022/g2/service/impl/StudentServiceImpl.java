package mk.ukim.finki.wp.kol2022.g2.service.impl;

import mk.ukim.finki.wp.kol2022.g2.model.Course;
import mk.ukim.finki.wp.kol2022.g2.model.Student;
import mk.ukim.finki.wp.kol2022.g2.model.StudentType;
import mk.ukim.finki.wp.kol2022.g2.model.exceptions.InvalidCourseIdException;
import mk.ukim.finki.wp.kol2022.g2.model.exceptions.InvalidStudentIdException;
import mk.ukim.finki.wp.kol2022.g2.repository.CourseRepository;
import mk.ukim.finki.wp.kol2022.g2.repository.StudentRepository;
import mk.ukim.finki.wp.kol2022.g2.service.StudentService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService, UserDetailsService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(InvalidStudentIdException::new);
    }

    @Override
    public Student create(String name, String email, String password, StudentType type, List<Long> courseId, LocalDate enrollmentDate) {
        List<Course> courses = courseRepository.findAllById(courseId);
        password = passwordEncoder.encode(password);
        Student student = new Student(name, email, password, type, courses, enrollmentDate);
        return studentRepository.save(student);
    }

    @Override
    public Student update(Long id, String name, String email, String password, StudentType type, List<Long> coursesId, LocalDate enrollmentDate) {
        Student student = findById(id);
        student.setName(name);
        student.setEmail(email);
        if (!password.equals(student.getPassword())){
            password = passwordEncoder.encode(password);
        }
        student.setPassword(password);
        student.setType(type);
        List<Course> courses = courseRepository.findAllById(coursesId);
        student.setCourses(courses);
        student.setEnrollmentDate(enrollmentDate);
        return studentRepository.save(student);
    }

    @Override
    public Student delete(Long id) {
        Student student = findById(id);
        studentRepository.delete(student);
        return student;
    }

    @Override
    public List<Student> filter(Long courseId, Integer yearsOfStudying) {
        if (courseId == null && yearsOfStudying == null ){
            return listAll();
        }else if (courseId == null){
            int startYear = (LocalDate.now().getYear() - yearsOfStudying);
            startYear = 2023 - yearsOfStudying;
            LocalDate date1 = LocalDate.of(startYear, 1, 1);
            LocalDate date2 = LocalDate.of(startYear, 12, 31);
            return studentRepository.findAllByEnrollmentDateBetween(date1, date2);
        }else if (yearsOfStudying == null){
            Course course = courseRepository.findById(courseId).orElseThrow(InvalidCourseIdException::new);
            return studentRepository.findAllByCoursesContaining(course);
        }else{
            int startYear = (LocalDate.now().getYear() - yearsOfStudying);
            startYear = 2023 - yearsOfStudying;
            LocalDate date1 = LocalDate.of(startYear, 1, 1);
            LocalDate date2 = LocalDate.of(startYear, 12, 31);
            Course course = courseRepository.findById(courseId).orElseThrow(InvalidCourseIdException::new);
            return studentRepository.findAllByCoursesContainingAndEnrollmentDateBetween(course, date1, date2);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(username);
        return new org.springframework.security.core.userdetails.User(
                student.getEmail(),
                student.getPassword(),
                Collections.singletonList(student.getType())
        );
    }
}
