package by.lobov.config;

import by.lobov.entity.HW37.Address;
import by.lobov.entity.HW37.Course;
import by.lobov.entity.HW37.Student;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class SessionConfig {
    private final HibernateConfig config;

    @Bean
    public SessionFactory getSessionFactory() {
        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            Properties settings = getProperties(); //получаем объект с заполненными нами данными

            configuration.setProperties(settings);

            //говорим hibernate что этот класс что в скобках
            // надо отслеживать (если там есть entity, то создавай таблицы и все что там сказано)
            configuration.addAnnotatedClass(Address.class);
            configuration.addAnnotatedClass(Course.class);
            configuration.addAnnotatedClass(Student.class);

            //для получения sessionFactory нужно так делать (это фабрика сессий)
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            return configuration.buildSessionFactory(serviceRegistry);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Properties getProperties() {
        //  настройки hibernate
        Properties settings = new Properties();
        settings.put(AvailableSettings.DRIVER, config.getDriver());
        settings.put(AvailableSettings.URL, config.getUrl());
        settings.put(AvailableSettings.USER, config.getUser());
        settings.put(AvailableSettings.PASS, config.getPassword());
        settings.put(AvailableSettings.DIALECT, config.getDialect()); //чтоб понимал версию hibernate

//        чтоб hibernate показывал нам логи и что он делает с БД
        settings.put(AvailableSettings.SHOW_SQL, "true"); //покажи нам какие запросы ты делаешь
        settings.put(AvailableSettings.FORMAT_SQL, "true"); //чтоб то, что покажет нам после
        // команды SHOW в красивом формате json

//        settings.put(AvailableSettings.AUTOCOMMIT, "true"); //для автоматического сохранения после операции (не подходит для транзакций)

        settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");

        settings.put(AvailableSettings.HBM2DDL_AUTO, "create-drop"); //update, none
        // Как только мы запустим приложение
        // У нас запустятся и создадутся все таблицы и значения. Эьа команда create drop означает чтоб
        // он по началу работы создал все таблицы по аналогии с классами которые мы написали, а после
        // работы все это удалится (обычно это для дебаг целей)
        //если поставить none - то hibernate без нас не сможет менять таблицы (по дефолту оно стоит)
        // (на промышленных проектах)

//        settings.put(AvailableSettings.HBM2DDL_AUTO, "update"); //ЗАНЯТИЕ 43

        return settings;
    }
}
