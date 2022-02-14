package pl.sda.java.adv.school;

import pl.sda.java.adv.school.model.Student;
import pl.sda.java.adv.school.util.CsvStudentsLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class StudentService {
    private final List<Student> students;

    public StudentService(List<Student> students) {
        this.students = students;
    }

    public Map<String,Student> getStudentIdToStudentMap() {
//        final Map<String,Student> studentIdToStudentMap = new HashMap<>();
//        for (Student student : students) {
//            studentIdToStudentMap.put(student.getId(), student);
//        }
//
//        return Collections.unmodifiableMap(studentIdToStudentMap);

        return students.stream()
            .collect(Collectors.toUnmodifiableMap(
                Student::getId, // student -> student.getId()
                student -> student // Function.identity()
            ));
    }

    public Map<String,List<Student>> getCityToStudentsMap() {
//        final Map<String,List<Student>> cityToStudentsMap = new HashMap<>();
//        for (Student student : students) {
//            final String city = student.getAddress().getCity();
//            List<Student> studentsFromCity = cityToStudentsMap.get(city);
//            if (studentsFromCity == null) {
//                studentsFromCity = new LinkedList<>();
//                cityToStudentsMap.put(city, studentsFromCity);
//            }
//
//            studentsFromCity.add(student);
//        }
//
//        return cityToStudentsMap;


//        final Map<String,List<Student>> cityToStudentsMap = new HashMap<>();
//        for (Student student : students) {
//            final String city = student.getAddress().getCity();
//            List<Student> studentsFromCity = cityToStudentsMap.computeIfAbsent(city, c -> new LinkedList<>());
//            studentsFromCity.add(student);
//        }
//
//        return cityToStudentsMap;

        return students.stream()
                .collect(Collectors.groupingBy(student -> student.getAddress().getCity()));
    }

    public static void main(String[] args) {

        CsvStudentsLoader csvStudentsLoader = new CsvStudentsLoader();
        List<Student> students;

        try (InputStream inputStream = Files.newInputStream(Path.of("students.csv"))) {
            students = csvStudentsLoader.loadData(inputStream);
        } catch (IOException e) {
            students = Collections.emptyList();
            e.printStackTrace();
        }

        students.forEach(System.out::println);

        List<Student> studentsToSort = new LinkedList<>(students);

        studentsToSort = studentsToSort.stream()
                .sorted(Comparator.comparing(Student::getBirthDate).reversed())
                .collect(Collectors.toList());

        System.out.println();

        studentsToSort.forEach(System.out::println);

        studentsToSort = studentsToSort.stream()
                .sorted(Comparator.comparing(Student::getBirthDate))
                .collect(Collectors.toList());

        System.out.println();

        studentsToSort.forEach(System.out::println);

        studentsToSort = studentsToSort.stream().sorted(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                int cityResult = o1.getAddress().getCity().compareTo(o2.getAddress().getCity());
                if (cityResult == 0) {
                    return o1.getLastName().compareTo(o2.getLastName());
                }
                return cityResult;
            }
        }).collect(Collectors.toList());

        System.out.println();

        studentsToSort.forEach(System.out::println);

        List<Student> studentsFiltered = studentsToSort.stream()
                .filter(s -> s.getSchoolYear() == 8)
                .sorted(new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        int lastNameResult = o1.getLastName().compareTo(o2.getLastName());
                        if (lastNameResult == 0) {
                            return o1.getFirstName().compareTo(o2.getFirstName());
                        }
                        return lastNameResult;
                    }
                }).collect(Collectors.toList());

        System.out.println();

        studentsFiltered.forEach(System.out::println);
    }

    public List<Student> getStudentsSortedByAgeAsc() {
        return students.stream()
                .sorted(Comparator.comparing(Student::getBirthDate).reversed())
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsSortedByAgeDesc() {
        return students.stream()
                .sorted(Comparator.comparing(Student::getBirthDate))
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsSortedByCityAndLastName() {
        return students.stream()
                .sorted(Comparator.comparing(student -> student.getAddress().getCity() + student.getLastName()))
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsByYearSortedByLastAndFirstName(byte classCode) {
        return students.stream()
                .filter(student -> student.getSchoolYear() == 8)
                .sorted(Comparator.comparing(student -> student.getLastName() + student.getFirstName()))
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsWhichRepeatedAYear() {
        return students.stream()
                .filter(student -> (LocalDate.now().getYear() - student.getSchoolYear() > student.getStartYear()))
                .collect(Collectors.toList());
    }

    public Map<String, Optional<Student>> getOldestStudentFromEachCity() {
        return students.stream().collect(Collectors.groupingBy(student -> student.getAddress().getCity(),
                Collectors.minBy(Comparator.comparing(Student::getBirthDate))));
    }

    public double getRatioOfStudentsNotFrom(String city) {
        return ((double) (students.stream().filter(student -> !student.getAddress().getCity().equals(city))
                .count()) / (double) students.size()*100);
    }
}
