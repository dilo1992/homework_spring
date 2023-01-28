package by.lobov.entity.HW37;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id_address")
    private Long id;

    @Column
    private String address;

    @OneToOne(mappedBy = "address", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private Student student;
}
