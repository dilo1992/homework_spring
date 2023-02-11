package by.lobov.annotation;

import by.lobov.entity.HW38.StudentForHw38;
import by.lobov.validator.HW38.MyValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {MyValidator.class})
public @interface SelectFromEnum {

    String message() default "It`s our annotation. This field should be contains in the enum";

    //эти два поля обязательные, где они применяются нам знать пока не обязательно, просто добавляем и все
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
