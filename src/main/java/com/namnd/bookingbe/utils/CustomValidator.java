package com.namnd.bookingbe.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class CustomValidator implements ConstraintValidator<CustomValidation, Object> {


    @Override
    public void initialize(CustomValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        // Lấy danh sách các trường của đối tượng value
        Field[] fields = value.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // Cho phép truy cập trường private

            // Kiểm tra xem trường có đánh dấu @NotNull hoặc @NotEmpty không
            Annotation[] annotations = field.getDeclaredAnnotations();
            boolean hasNotNullAnnotation = false;
            boolean hasNotEmptyAnnotation = false;

            for (Annotation annotation : annotations) {
                if (annotation instanceof NotNull) {
                    hasNotNullAnnotation = true;
                }
                if (annotation instanceof NotEmpty) {
                    hasNotEmptyAnnotation = true;
                }
            }

            if (hasNotNullAnnotation || hasNotEmptyAnnotation) {
                try {
                    // Lấy giá trị của trường
                    Object fieldValue = field.get(value);

                    // Kiểm tra giá trị không null và không rỗng (nếu là một chuỗi)
                    if (hasNotNullAnnotation && fieldValue == null) {
                        return false;
                    }
                    if (hasNotEmptyAnnotation && (fieldValue == null || fieldValue.toString().isEmpty())) {
                        return false;
                    }
                } catch (IllegalAccessException e) {
                    // Xử lý nếu không thể truy cập trường
                    e.printStackTrace();
                }
            }
        }

        return true; // Tất cả các trường không null và không rỗng
    }
}
