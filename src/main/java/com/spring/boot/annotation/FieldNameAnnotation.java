package com.spring.boot.annotation;


import java.lang.annotation.*;

/**
 * 操作日志属性注解
 * @author wangyt
 * @version 1.0 created 2018/8/28
 */
@Inherited
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldNameAnnotation {


    /**
     * 字段名称
     *
     * @return str
     */
    String name() default "";

    /**
     * 字段名称
     *
     * @return str
     */
    String dateFormat() default "yyyy-MM-dd";
}
