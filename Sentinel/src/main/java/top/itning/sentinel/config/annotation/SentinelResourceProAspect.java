package top.itning.sentinel.config.annotation;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.ResourceTypeConstants;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.aspectj.AbstractSentinelAspectSupport;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 注解AOP
 *
 * @author itning
 * @date 2020/8/22 16:58
 */
@Aspect
public class SentinelResourceProAspect extends AbstractSentinelAspectSupport {
    private static final Logger logger = LoggerFactory.getLogger(SentinelResourceProAspect.class);

    @Pointcut("@annotation(top.itning.sentinel.config.annotation.SentinelResourcePro)")
    public void sentinelResourceAnnotationPointcut() {
    }

    @Around("sentinelResourceAnnotationPointcut()")
    public Object invokeResourceWithSentinel(ProceedingJoinPoint pjp) throws Throwable {
        Method originMethod = resolveMethod(pjp);

        SentinelResourcePro annotation = originMethod.getAnnotation(SentinelResourcePro.class);
        if (annotation == null) {
            // Should not go through here.
            throw new IllegalStateException("Wrong state for SentinelResource annotation");
        }
        String resourceName = getResourceName(annotation.value(), originMethod);
        EntryType entryType = annotation.entryType();
        Entry entry = null;
        try {
            ContextUtil.enter(resourceName);
            entry = SphU.entry(resourceName, ResourceTypeConstants.COMMON_WEB, entryType, pjp.getArgs());
            return pjp.proceed();
        } catch (BlockException ex) {
            logger.warn(ex.getRuleLimitApp(), ex);
            return null;
        } catch (Throwable ex) {
            // 为了统计异常比例或异常数，需要通过 Tracer.trace(ex) 记录业务异常。
            traceException(ex);
            throw ex;
        } finally {
            if (entry != null) {
                entry.exit(1, pjp.getArgs());
            }
            ContextUtil.exit();
        }
    }
}
