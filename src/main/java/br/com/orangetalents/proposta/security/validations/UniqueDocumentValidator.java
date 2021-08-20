package br.com.orangetalents.proposta.security.validations;

import br.com.orangetalents.proposta.security.handler.DuplicatedDocumentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.List;

public class UniqueDocumentValidator implements ConstraintValidator<UniqueDocument, String> {


    private String domainAttribute;
    private Class<?> Klass;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueDocument params) {
        this.domainAttribute = params.fieldName();
        this.Klass = params.domainClass();
    }

    @Override
    public boolean isValid(String document, ConstraintValidatorContext constraintValidatorContext) {
        Query query = entityManager.createQuery("SELECT 1 FROM "+Klass.getName()+" where "+domainAttribute+"=:value");
        query.setParameter("value", document);
        List<?> list = query.getResultList();
        if (list.size() >= 1)
            throw new DuplicatedDocumentException(domainAttribute, "Foi encontrado mais de uma + "+Klass.getName()+" com o documento: "+document);

        return true;
    }
}
