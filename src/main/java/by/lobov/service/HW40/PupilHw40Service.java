package by.lobov.service.HW40;

import by.lobov.entity.HW40.Pupil;
import by.lobov.repository.HW40.PupilHw40Repository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PupilHw40Service {

    private final PupilHw40Repository repository;

    public List<Pupil> findAll() {
        return repository.findAll();
    }

    public Pupil findById(Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<Pupil> findByCourse(int course) {
        return repository.findAllByCourse(course);
    }

    public void save(Pupil pupil) {
        repository.save(pupil);
    }

    public void delete(Pupil pupil) {
        repository.delete(pupil);
    }
}
