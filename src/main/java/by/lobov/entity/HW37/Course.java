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
@Table(name = "courses_hw37")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "count_of_students")
    private Integer countOfStudents;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Student> studentsInCourse = new ArrayList<>();

    public void addStudentsInCourse(Student student) {
        student.setCourse(this);
        studentsInCourse.add(student);
    }
}
