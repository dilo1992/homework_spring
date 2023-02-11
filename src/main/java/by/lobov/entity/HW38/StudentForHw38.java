package by.lobov.entity.HW38;

import by.lobov.annotation.SelectFromEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students_hw38")
public class StudentForHw38 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Length(min = 5, message = "this name is too short")
    private String name;

    @Column(name = "course")
    @SelectFromEnum(message = "this is a non-transmission field")
    private String course;
}
