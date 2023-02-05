package by.lobov.repository.HW38;

import by.lobov.entity.HW38.StudentForHw38;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentForHw38Repository {
    private final SessionFactory factory; //подтянули bean SessionFactory из класса SessionConfig

    public StudentForHw38 save(StudentForHw38 student) {
        try (Session session = factory.openSession()) {  //открываем сессию для полного соединения с БД
            Transaction transaction = session.beginTransaction(); //открой транзакцию
            // (это для выполнения нескольких операций, пока не скажем не записывает)
            if (student.getId() == null) {
                session.persist(student); //сохраняем полностью (аналог insert, если нет такой id, то вставить)
            } else {
                session.merge(student);  //сохраняем апдейтом (обновляем) (если id такой есть в БД)
            }
            transaction.commit(); //заверши транзакцию и запиши (закомитить)
        }
        return student;
    }

    public Optional<StudentForHw38> findById(Long id) {
        StudentForHw38 student = null;
        try (Session session = factory.openSession()) {
            student = session.find(StudentForHw38.class, id);
        }
        return Optional.ofNullable(student);
    }

    public List<StudentForHw38> findAll() {
        List<StudentForHw38> students;
        try (Session session = factory.openSession()) {
            students = session
                    .createQuery("from StudentForHw38", StudentForHw38.class) //отдельно для hibernate
                    // такие Query применяются (на как в SQL)
                    .getResultList();
        }
        return students;
    }

    public List<StudentForHw38> findByCourse(String course) {
        return findAll().stream().filter(student -> student.getCourse().equals(course)).toList();
    }

    public void delete(Long id) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            findById(id).ifPresent(student -> session.remove(student));
            transaction.commit();
        }
    }
}
