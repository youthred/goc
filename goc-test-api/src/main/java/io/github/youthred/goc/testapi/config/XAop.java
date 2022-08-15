package io.github.youthred.goc.testapi.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class XAop {

    @Around("@annotation(x)")
    public Object method(ProceedingJoinPoint pjp, X x) throws Throwable {
        log.info(x.value());
        return pjp.proceed();
    }

    @Around("@within(x)")
    public Object classPr(ProceedingJoinPoint pjp, X x) throws Throwable {
        log.info(x.value());
        return pjp.proceed();
    }
}
