package ${package}.domain.shared;

import com.google.common.collect.Sets;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.Date;
import java.util.Set;

/**
 * 带id的值对象 Created by jiangwenkang on 16-11-7.
 */
public abstract class IdentifiedValueObject<V extends IdentifiedValueObject> implements ValueObject<V> {
    private static final long serialVersionUID = -7815856088988915247L;
    protected static Validator validator;

    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    protected Long id;
    protected Date gmtCreate;
    protected Date gmtModify;

    public static <T extends IdentifiedValueObject> Set<String> validate(T entityInstance) {
        Set<ConstraintViolation<T>> resultSet = validator.validate(entityInstance, Default.class);
        Set<String> errorMessages = Sets.newHashSet();
        resultSet.forEach(error -> errorMessages.add(error.getMessage()));
        return errorMessages;
    }

    protected Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    protected void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    protected void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    @Override
    public String toString() {
        return "IdentifiedValueObject{" + "id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + '}';
    }
}
