package by.lobov.repository.HW40;

import by.lobov.entity.HW40.Pupil;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PupilHw40Repository extends JpaRepository<Pupil, Long> {

    List<Pupil> findAllByCourse(int course);
}
