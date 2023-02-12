package by.lobov.entity.HW40;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pupils_hw40")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pupil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "this field cannot be empty")
    private String name;

    @Column(name = "course")
    @Min(value = 1, message = "a group number cannot be less than 1")
    @Max(value = 100, message = "a group number cannot be more than 100")
    private Long course;
}
