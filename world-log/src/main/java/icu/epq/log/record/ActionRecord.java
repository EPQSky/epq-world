package icu.epq.log.record;

import lombok.Data;

import java.io.Serializable;

/**
 * 行为记录实体类
 *
 * @author epqsky
 */
@Data
public class ActionRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数值
     */
    private Object[] argsValue;

    /**
     * 行为时间
     */
    private Long actionTime;
}
