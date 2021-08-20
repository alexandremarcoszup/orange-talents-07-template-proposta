package br.com.orangetalents.proposta.security.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {
    String message() default "{br.com.mercadolivre.validator.uniquevalue}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};

    String fieldName();

    Class<?> domainClass();
}
