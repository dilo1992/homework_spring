package by.lobov.entity.HW37;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "addresses_hw37")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @OneToOne(mappedBy = "address", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private Student student;
}
