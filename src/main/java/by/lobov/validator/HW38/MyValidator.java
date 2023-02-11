package by.lobov.validator.HW38;

import by.lobov.annotation.SelectFromEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MyValidator implements ConstraintValidator<SelectFromEnum, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean exist = true;
        try {
            CoursesForHw38.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            exist = false;
        }
        return exist;
    }
}
