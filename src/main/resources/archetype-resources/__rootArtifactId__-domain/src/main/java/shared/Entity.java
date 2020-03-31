package ${package}.domain.shared;

import com.google.common.collect.Sets;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 实体基类
 *
 * Created by jiangwenkang on 16-11-2.
 */
public class Entity implements Serializable {
    private static final long serialVersionUID = -6061398519907741750L;
    protected static Validator validator;

    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    protected Long id;
    protected Date gmtCreate;
    protected Date gmtModify;
    protected IsDeleted isDeleted;

    public static <T extends Entity> Set<String> validate(T entityInstance) {
        Set<ConstraintViolation<T>> resultSet = validator.validate(entityInstance, Default.class);
        Set<String> errorMessages = Sets.newHashSet();
        resultSet.forEach(error -> errorMessages.add(error.getMessage()));
        return errorMessages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public IsDeleted getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(IsDeleted isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isDeleted() {
        return isDeleted == IsDeleted.y;
    }

    protected Set<String> validate() {
        throw new UnsupportedOperationException("not implement");
    }

    @Override
    public String toString() {
        return "Entity{" + "id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModify=" + gmtModify + ", isDeleted="
                + isDeleted + '}';
    }

    public enum IsDeleted {
        y, n
    }
}
