package Kolokvium_2.Faculty;

//package mk.ukim.finki.vtor_kolokvium;

import java.util.*;
import java.util.stream.Collectors;

public class FacultyTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        if (testCase == 1) {
            System.out.println("TESTING addStudent AND printFirstNStudents");
            Faculty faculty = new Faculty();
            for (int i = 0; i < 10; i++) {
                faculty.addStudent("student" + i, (i % 2 == 0) ? 3 : 4);
            }
            faculty.printFirstNStudents(10);

        } else if (testCase == 2) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            try {
                faculty.addGradeToStudent("123", 7, "NP", 10);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
            try {
                faculty.addGradeToStudent("1234", 9, "NP", 8);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        } else if (testCase == 3) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("123", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("1234", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else if (testCase == 4) {
            System.out.println("Testing addGrade for graduation");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            int counter = 1;
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("123", i, "course" + counter, (i % 2 == 0) ? 7 : 8);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            counter = 1;
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("1234", i, "course" + counter, (j % 2 == 0) ? 7 : 10);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("PRINT STUDENTS (there shouldn't be anything after this line!");
            faculty.printFirstNStudents(2);
        } else if (testCase == 5 || testCase == 6 || testCase == 7) {
            System.out.println("Testing addGrade and printFirstNStudents (not graduated student)");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), i % 5 + 6);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            if (testCase == 5)
                faculty.printFirstNStudents(10);
            else if (testCase == 6)
                faculty.printFirstNStudents(3);
            else
                faculty.printFirstNStudents(20);
        } else if (testCase == 8 || testCase == 9) {
            System.out.println("TESTING DETAILED REPORT");
            Faculty faculty = new Faculty();
            faculty.addStudent("student1", ((testCase == 8) ? 3 : 4));
            int grade = 6;
            int counterCounter = 1;
            for (int i = 1; i < ((testCase == 8) ? 6 : 8); i++) {
                for (int j = 1; j < 3; j++) {
                    try {
                        faculty.addGradeToStudent("student1", i, "course" + counterCounter, grade);
                    } catch (OperationNotAllowedException e) {
                        e.printStackTrace();
                    }
                    grade++;
                    if (grade == 10)
                        grade = 5;
                    ++counterCounter;
                }
            }
            System.out.println(faculty.getDetailedReportForStudent("student1"));
        } else if (testCase==10) {
            System.out.println("TESTING PRINT COURSES");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            faculty.printCourses();
        } else if (testCase==11) {
            System.out.println("INTEGRATION TEST");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 2 : 3); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }

            }

            for (int i=11;i<15;i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= 3; k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("DETAILED REPORT FOR STUDENT");
            System.out.println(faculty.getDetailedReportForStudent("student2"));
            try {
                System.out.println(faculty.getDetailedReportForStudent("student11"));
                System.out.println("The graduated students should be deleted!!!");
            } catch (NullPointerException e) {
                System.out.println("The graduated students are really deleted");
            }
            System.out.println("FIRST N STUDENTS");
            faculty.printFirstNStudents(10);
            System.out.println("COURSES");
            faculty.printCourses();
        }
    }
}
class OperationNotAllowedException extends Exception{
    public OperationNotAllowedException(String msg) {
        super(msg);
    }
}
class Course{
    String courseName;
    int grade;
    public Course(String courseName, int grade) {
        this.courseName = courseName;
        this.grade = grade;
    }
    public int getGrade() {
        return grade;
    }
    public String getCourseName() {
        return courseName;
    }
    @Override
    public String toString() {
        return courseName;
    }
}
class FullCourse{
    String courseName;
    int countOfStudents=0;
    List<Integer> grades;

    public FullCourse(String courseName) {
        this.courseName = courseName;
        this.grades=new LinkedList<>();
    }
    public void addStudentToCourse(Integer grade){
        countOfStudents++;
        grades.add(grade);
    }

    public int getCountOfStudents() {
        return countOfStudents;
    }

    public double getAverageGrade() {
        if(countOfStudents==0){
            return 5.0;
        }
        return grades.stream().mapToInt(i->i).summaryStatistics().getAverage();
    }

    public String getCourseName() {
        return courseName;
    }
}
class Student{
    String id;
    int yearsOfStudies;
    Map<Integer, List<Course>> mapByTerm;
    public Student(String id, int yearsOfStudies) {
        this.id = id.toLowerCase();
        this.yearsOfStudies=yearsOfStudies;
        this.mapByTerm=new TreeMap<>();
    }
    public String getId() {
        return id;
    }
    public int getYearsOfStudies(){
        return yearsOfStudies;
    }
    public void addGrade(int term, String courseName, int grade) throws OperationNotAllowedException{
        if(term>yearsOfStudies*2){
            throw new OperationNotAllowedException (String.format("Term %d is not possible for student with ID %s",term, id));
        }
        if(!mapByTerm.containsKey(term)){
            mapByTerm.put(term, new LinkedList<>());
        }if(mapByTerm.get(term).size()>=3){
            throw new OperationNotAllowedException (String.format("Student %s already has 3 grades in term %d",id, term));
        }
        mapByTerm.get(term).add(new Course(courseName, grade));
    }
    public boolean isGraduated(){
        if(getNumberOfPassedSubjects()==yearsOfStudies*6){
            return true;
        }else return false;
    }
    public int getNumberOfPassedSubjects(){
        return mapByTerm.values().stream().flatMap(Collection::stream).collect(Collectors.toList()).size();
    }
    public double getAverageGrade(){
        int sum = mapByTerm.values().stream().flatMap(sem->sem.stream()).mapToInt(sub->sub.getGrade()).sum();
        if(sum==0){
            return 5.0;
        }
        return (double) sum/getNumberOfPassedSubjects();
    }
    public String toString() {
        return String.format("Student: %s Courses passed: %d Average grade: %.2f",id, getNumberOfPassedSubjects(), getAverageGrade());
    }
    public double averagePerTerm(int term){
        if(!mapByTerm.containsKey(term)){
            return 5.0;
        }
        return mapByTerm.get(term).stream().mapToInt(course-> course.getGrade()).summaryStatistics().getAverage();
    }
    public int getCoursesPerTerm(int term){
        if(!mapByTerm.containsKey(term)){
            return 0;
        }
        return mapByTerm.get(term).size();
    }
    public  String getDetailedReport(){
        StringBuilder sb= new StringBuilder();
        sb.append("Student: ").append(id).append("\n");
        for(int i=1;i<=yearsOfStudies*2;i++){
            sb.append("Term "+i+"\n");
            sb.append("Courses: "+getCoursesPerTerm(i)+"\n");
            sb.append(String.format("Average grade for term: %.2f\n", averagePerTerm(i)));
        }
        sb.append(String.format("Average grade: %.2f\n", getAverageGrade()));
        sb.append("Courses attended: ");
        List<Course> coursesList=mapByTerm.values().stream().flatMap(p->p.stream())
                .sorted(Comparator.comparing(Course::getCourseName))
                .collect(Collectors.toList());
        sb.append(coursesList.get(0));
        for(int i=1;i<coursesList.size();i++){
            sb.append(","+coursesList.get(i));
        }
        return sb.toString();
    }

}

class Faculty {
    Map<String, Student> studentMap;
    List<String> graduatedList;
    Map<String, FullCourse> coursesMap;
    public Faculty() {
        this.studentMap=new TreeMap<>(Comparator.reverseOrder());
        this.graduatedList = new LinkedList<>();
        this.coursesMap=new TreeMap<>();
    }
    void addStudent(String id, int yearsOfStudies) {
        if(!studentMap.containsKey(id)){
            studentMap.put(id, new Student(id, yearsOfStudies));
        }
    }
    void addGradeToStudent(String studentId, int term, String courseName, int grade) throws OperationNotAllowedException {
        studentMap.get(studentId).addGrade(term, courseName, grade);
        if(studentMap.get(studentId).isGraduated()){
            graduatedList.add(String.format("Student with ID %s graduated with average grade %.2f in %d years."
                    , studentMap.get(studentId).getId(), studentMap.get(studentId).getAverageGrade()
                    ,studentMap.get(studentId).getYearsOfStudies()));
            studentMap.remove(studentId);
        }
        if(!coursesMap.containsKey(courseName)){
            coursesMap.put(courseName, new FullCourse(courseName));
        }
        coursesMap.get(courseName).addStudentToCourse(grade);
    }
    String getFacultyLogs() {
        StringBuilder sb = new StringBuilder();
        sb.append(graduatedList.get(0));
        for(int i=1;i<graduatedList.size();i++){
            sb.append("\n").append(graduatedList.get(i));
        }
        return sb.toString();
    }

    void printFirstNStudents(int n) {
        studentMap.values().stream().sorted(Comparator.comparing(Student::getNumberOfPassedSubjects)
                        .thenComparing(Student::getAverageGrade).reversed())
                .limit(n)
                .forEach(std-> System.out.println(std.toString()));
    }
    String getDetailedReportForStudent(String id) {
        return studentMap.get(id).getDetailedReport().toString();
    }
    void printCourses() {
        coursesMap.values()
                .stream().sorted(Comparator.comparing(FullCourse::getCountOfStudents)
                        .thenComparing(FullCourse::getAverageGrade))
                .forEach(course-> System.out.printf("%s %d %.2f%n", course.getCourseName(), course.getCountOfStudents(), course.getAverageGrade()));
    }
}
