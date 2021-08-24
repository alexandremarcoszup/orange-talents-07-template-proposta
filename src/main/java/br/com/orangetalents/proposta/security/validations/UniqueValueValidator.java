package br.com.orangetalents.proposta.security.validations;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String domainAttribute;
    private Class<?> Klass;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueValue params) {
        this.domainAttribute = params.fieldName();
        this.Klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        Query query = entityManager.createQuery("SELECT 1 FROM " + Klass.getName() + " where " + domainAttribute + "=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();

        Assert.state(list.size() <= 1, "Foi encontrado mais de um " + this.Klass.getName() + " com o mesmo atributo " + this.domainAttribute + " = " + value);

        return list.isEmpty();
    }
}
