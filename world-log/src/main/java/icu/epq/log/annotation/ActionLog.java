package icu.epq.log.annotation;

import java.lang.annotation.*;

/**
 * 行为日志注解
 *
 * @author epqsky
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActionLog {

    /**
     * 日志描述
     *
     * @return
     */
    String value() default "用户行为日志";

}
