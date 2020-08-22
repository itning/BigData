package top.itning.sentinel.config.annotation;

import com.alibaba.csp.sentinel.EntryType;

import java.lang.annotation.*;

/**
 * @author itning
 * @date 2020/8/22 16:56
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SentinelResourcePro {
    String value() default "";

    EntryType entryType() default EntryType.OUT;
}
