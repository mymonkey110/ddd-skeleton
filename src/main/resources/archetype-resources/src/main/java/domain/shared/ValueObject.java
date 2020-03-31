package ${package}.domain.shared;

import java.io.Serializable;

/**
 * 值对象接口
 * <p>
 * Created by jiangwenkang on 16-11-2.
 */
public interface ValueObject<T> extends Serializable {

    boolean isSameWith(T other);
}
