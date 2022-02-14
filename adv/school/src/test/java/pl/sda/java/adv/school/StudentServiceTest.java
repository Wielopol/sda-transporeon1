package pl.sda.java.adv.school;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.java.adv.school.model.Student;
import pl.sda.java.adv.school.util.CsvStudentsLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class StudentServiceTest {
    private StudentService studentService;

    @BeforeEach
    void setUp() throws IOException {
        final CsvStudentsLoader csvStudentsLoader = new CsvStudentsLoader();
        final List<Student> students;

        try (final InputStream inputStream = StudentServiceTest.class.getResourceAsStream("/csv/students.csv")) {
            students = csvStudentsLoader.loadData(inputStream);
        }

        studentService = new StudentService(students);
    }

    @Test
    void testGetStudentIdToStudentMap() {
        //WHEN
        final Map<String, Student> map = studentService.getStudentIdToStudentMap();

        //THEN
        Student student1 = map.get("00001001");
        assertThat(student1).isNotNull();
        assertThat(student1.getLastName()).isEqualTo("Kowalski");
    }

    @Test
    void testGetCityToStudentsMap() {
        //WHEN
        final Map<String,List<Student>> map = studentService.getCityToStudentsMap();

        //THEN
        assertThat(map.get("Krzeszowice"))
            .hasSize(2)
            .extracting(Student::getId)
            .containsExactlyInAnyOrder("00001003","00001007");
    }

    @Test
    void getStudentsSortedByAge() {
        //WHEN
        var students = studentService.getStudentsSortedByAgeAsc();

        //THEN
        assertThat(students).extracting(Student::getId)
                .containsExactly(
                        "00001009",
                        "00001005",
                        "00001007",
                        "00001006",
                        "00001002",
                        "00001001",
                        "00001298",
                        "00001004",
                        "00001003",
                        "00001008",
                        "00002005",
                        "00002002",
                        "00002004",
                        "00002001",
                        "00002003"
                );
    }

    @Test
    void getStudentsSortedByAgeDesc() {
        //WHEN
        var students = studentService.getStudentsSortedByAgeDesc();

        //THEN
        assertThat(students).extracting(Student::getId)
                .containsExactly(
                        "00002003",
                        "00002001",
                        "00002004",
                        "00002002",
                        "00002005",
                        "00001008",
                        "00001003",
                        "00001004",
                        "00001298",
                        "00001001",
                        "00001002",
                        "00001006",
                        "00001007",
                        "00001005",
                        "00001009"
                );
    }

    @Test
    void getStudentsSortedByCityAndLastName() {
        //WHEN
        var students = studentService.getStudentsSortedByCityAndLastName();

        //THEN
        assertThat(students).extracting(Student::getId)
                .containsExactly(
                        "00002002",
                        "00001001",
                        "00002003",
                        "00001298",
                        "00001009",
                        "00001004",
                        "00001008",
                        "00001007",
                        "00001003",
                        "00002005",
                        "00001005",
                        "00001002",
                        "00002001",
                        "00001006",
                        "00002004"
                );
    }

    @Test
    void getStudentsByYearSortedByLastAndFirstName() {
        //WHEN
        var students = studentService.getStudentsByYearSortedByLastAndFirstName((byte)8);

        //THEN
        assertThat(students).extracting(Student::getLastName)
                .containsExactly(
                        "Dąbrowska",
                        "Jankowska",
                        "Kozłowski",
                        "Mazur",
                        "Woźniak"
                );
    }

    @Test
    void getStudentsWhichRepeatedAYear() {
        //WHEN
        var students = studentService.getStudentsWhichRepeatedAYear();

        //THEN
        assertThat(students).extracting(Student::getId)
                .containsExactlyInAnyOrder("00001003", "00001008", "00002004");
    }

//    @Test
//    void getOldestStudentFromEachCity() {
//        //WHEN
//        var cityToStudentMap = studentService.getOldestStudentFromEachCity();
//
//        //THEN
//        assertThat(cityToStudentMap.size()).isEqualTo(8);
//        assertThat(cityToStudentMap.get("Balice").getId()).isEqualTo("00002002");
//        assertThat(cityToStudentMap.get("Kłaj").getId()).isEqualTo("00002005");
//        assertThat(cityToStudentMap.get("Kraków").getId()).isEqualTo("00002003");
//        assertThat(cityToStudentMap.get("Krzeszowice").getId()).isEqualTo("00001003");
//        assertThat(cityToStudentMap.get("Modlniczka").getId()).isEqualTo("00001005");
//        assertThat(cityToStudentMap.get("Skawina").getId()).isEqualTo("00002001");
//        assertThat(cityToStudentMap.get("Wieliczka").getId()).isEqualTo("00001006");
//        assertThat(cityToStudentMap.get("Zabierzów").getId()).isEqualTo("00002004");
//    }
//
//    @Test
//    void getRatioOfStudentsNotFrom() {
//        //WHEN
//        var ratio = studentService.getRatioOfStudentsNotFrom("Kraków");
//
//        //THEN
//        assertThat(ratio).isCloseTo(60.0, Offset.offset(0.001));
//    }
}
