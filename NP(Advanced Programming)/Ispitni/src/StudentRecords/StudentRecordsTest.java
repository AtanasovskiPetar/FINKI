package StudentRecords;

import java.io.*;
import java.util.*;

/**
 * January 2016 Exam problem 1
 */
public class StudentRecordsTest {
    public static void main(String[] args) {
        System.out.println("=== READING RECORDS ===");
        StudentRecords studentRecords = new StudentRecords();
        int total;
        try {
            total = studentRecords.readRecords(System.in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Total records: %d\n", total);
        System.out.println("=== WRITING TABLE ===");
        studentRecords.writeTable(System.out);
        System.out.println("=== WRITING DISTRIBUTION ===");
        studentRecords.writeDistribution(System.out);
    }
}

// your code here
class Student{
    String studentCode;
    String course;
    List<Integer> gradeList;

    public Student(String studentCode, String course, List<Integer> gradeList) {
        this.studentCode = studentCode;
        this.course = course;
        this.gradeList = gradeList;
    }
    public double getAverageGrade(){
        return gradeList.stream().mapToInt(i->i).summaryStatistics().getAverage();
    }
    public String getStudentCode() {
        return studentCode;
    }

    @Override
    public String toString() {
        //lfxrv7 8.44
        return String.format("%s %.2f", studentCode, getAverageGrade());
    }

}
class GradeWithfrequency{
    int grade;
    int frequency;
    String courseName;
    public GradeWithfrequency(int grade, String courseName) {
        this.grade = grade;
        this.frequency=0;
        this.courseName = courseName;
    }


    public int getFrequency() {
        return frequency;
    }
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        // 6 | *************(124)
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<(frequency/10);i++){
            sb.append("*");
        }
        if(frequency%10 != 0){
            sb.append("*");
        }
        return String.format("%2d | %s(%d)",grade, sb, frequency);
    }

    public String getCourseName() {
        return courseName;
    }
}
class StudentRecords {
    Map<String, List<Student>> mapByCourse;
    Map<String, Map<Integer, GradeWithfrequency>> mapForGradefrequency;
    StudentRecords(){
        this.mapByCourse = new TreeMap<>(Comparator.naturalOrder());
        this.mapForGradefrequency = new TreeMap<>();
    }
    public int readRecords(InputStream inputStream) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        int totalRead=0;
        while((line=bf.readLine())!=null && line.length()!=0){
            String [] parts = line.split(" ");
            String code = parts[0];
            String course = parts[1];
            List<Integer> grades = new LinkedList<>();
            for(int i=2;i<parts.length;i++){
                grades.add(Integer.parseInt(parts[i]));
            }
            if(!mapByCourse.containsKey(course)){
                mapByCourse.put(course, new LinkedList<>());
            }
            mapByCourse.get(course).add(new Student(code, course, grades));

            if(!mapForGradefrequency.containsKey(course)){
                mapForGradefrequency.put(course, new HashMap<>());
            }
            for(int i=0;i<grades.size();i++){
                int g = grades.get(i);
                GradeWithfrequency grade1 = new GradeWithfrequency(grades.get(i), course);
                if(!mapForGradefrequency.get(course).containsKey(g)){
                    mapForGradefrequency.get(course).put(g, grade1);
                }
                mapForGradefrequency.get(course).get(g).setFrequency(mapForGradefrequency.get(course).get(g).getFrequency()+1);
            }

            totalRead++;


        }
        return totalRead;
    }
    void writeTable(OutputStream outputStream){
        PrintWriter pw = new PrintWriter(outputStream);
        mapByCourse.forEach((k,v)->{
            pw.println(k);
            v.stream().sorted(Comparator.comparing(Student::getAverageGrade).reversed().thenComparing(Student::getStudentCode))
                    .forEach(student -> pw.println(student.toString()));
        });
        pw.flush();
    }
    public static int getNumberOfTens(Map<Integer, GradeWithfrequency> map){
        return map.get(10).getFrequency();
    }
    void writeDistribution(OutputStream outputStream){
        Comparator<Map<Integer, GradeWithfrequency>> comparator = Comparator.comparing(val->getNumberOfTens(val));
        Comparator<Map<Integer, GradeWithfrequency>> comparator2 = comparator.reversed();
        PrintWriter prw = new PrintWriter(outputStream);
        mapForGradefrequency.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(comparator2))
                                .forEach(entry->{
                                    prw.println(entry.getKey());
                                    entry.getValue().values().forEach(v->prw.println(v.toString()));
                                });
        prw.flush();
    }
}
class ComparatorForDistribution implements Comparator<Map<Integer, GradeWithfrequency>>{
    @Override
    public int compare(Map<Integer, GradeWithfrequency> o1, Map<Integer, GradeWithfrequency> o2) {
        return Integer.compare(StudentRecords.getNumberOfTens(o2), StudentRecords.getNumberOfTens(o1));
    }
}