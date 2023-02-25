package by.lobov.repository.HW37;

import by.lobov.entity.HW37.Student;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentRepository {
    private final SessionFactory factory; //подтянули bean SessionFactory из класса SessionConfig

    public Student save(Student student) {
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

    public Optional<Student> findById(Long id) {
        Student student;
        try (Session session = factory.openSession()) {
            student = session.find(Student.class, id);
        }
        return Optional.ofNullable(student);
    }

    public List<Student> findAll() {
        List<Student> students;
        try (Session session = factory.openSession()) {
            students = session
                    .createQuery("from Student", Student.class) //отдельно для hibernate
                    // такие Query применяются (на как в SQL)
                    .getResultList();
        }
        return students;
    }

    public void delete(Long id) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            findById(id).ifPresent(student -> session.remove(student));
//            findById(id).ifPresent(session::remove);
//            то же самое что и сверху но выражение просто другое (IDEA так советует)
            transaction.commit();
        }

    }
}
