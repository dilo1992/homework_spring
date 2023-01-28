package by.lobov.entity.HW37;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    private Long id;

    @Column
    private String specialization;

    @Column
    private int countOfStudents;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Student> studentsInCourse = new ArrayList<>();

    public void addStudentsInCourse(Student student) {
        student.setCourse(this);
        studentsInCourse.add(student);
    }
}
