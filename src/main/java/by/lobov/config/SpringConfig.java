package by.lobov.config;

import by.lobov.entity.HW38.CountOfVisitHw38;
import by.lobov.interceptor.HW38.CountOfStudentsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("by.lobov")
public class SpringConfig implements WebMvcConfigurer { //WebMvcConfigurer - место где MVC соединяет все бины

    //по сути создаем экземпляр класса (или бин), чтоб если потом менять что-то,
    // то не менять по всему проекту, а только тут
    //@EnableWebMvc поможет включить Spring MVC без дополнительных xml настроек

    @Bean
    public CountOfStudentsInterceptor getCountOfStudentsInterceptor() {
        return new CountOfStudentsInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getCountOfStudentsInterceptor());
    }

    @Bean
    public CountOfVisitHw38 getCountOfVisit() {
        return new CountOfVisitHw38();
    }

    //переопределив данный метод мы сможем указать где будут лежать ресурсы нашего проекта, такие как css, image, js и другие.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/templates/**").addResourceLocations("/templates/");
    }

//    аналогичная конфигурация с mvc-dispatcher-servlet.xml.<bean
//	class="org.springframework.web.servlet.view.InternalResourceViewResolver">
//	<property name="prefix">
//		<value>/WEB-INF/pages/</value>
//	</property>
//	<property name="suffix">
//		<value>.jsp</value>
//	</property>
//</bean>
    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/resources/templates/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }
}
