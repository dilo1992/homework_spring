package by.lobov.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@Aspect //Сохраняет например логи для использования методов по всему проекту
// (здесь будут храниться все действия для определенного метода)

public class LoggingAspect {

    @Pointcut("execution(* *.save(..))") //(первая * - любые методы(private, protected, public и тд),
    //вторая * - мне не важен путь, главное чтоб метод назывался save
    // (..) - у этого метода могут быть параметры, а могут и не быть (если не нужны параметры - то без точек))
    public void logSaves() {
    }

    //перед методом хотим чтоб что-то делалось
    // (перед методами с именем save (определяем в Pointcut) будет печатать логи)
    @Before("by.lobov.aop.LoggingAspect.logSaves()")
    public void addLogs(JoinPoint joinPoint) {
        log.info("this log is from advice {}", joinPoint.getSignature()); //getSignature указывает перед каким
        // методом вызывается наша штука

        log.info("these arg: {}", new ArrayList<>(List.of(joinPoint.getArgs())));
    }



    //после возврата данных методом
    @AfterReturning(value = "by.lobov.aop.LoggingAspect.logSaves()", returning = "obj")
    public void addReturningLogs(JoinPoint joinPoint, Object obj) {
        log.info("we have returned {}", obj);
    }

    //вокруг метода
    @Around("by.lobov.aop.LoggingAspect.deletePointcut()")
    public void doNotDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        //ProceedingJoinPoint разница в том что этот поинт должен сказать когда нужно выполнять метод
        //потому что метод должн выполняться посредине, а вокруг то что в теперешнем методе (Around)
        //и вот точка jointPoint - это точка выполнения метода (мол здесь выполнить метод)

        log.info("Called for: {}", joinPoint.getSignature());
        //тут выполняется до выполнения метода
        joinPoint.proceed();
        //тут выполняется после выполнения метода
    }

    @Pointcut("execution(* delete(..))")
    public void deletePointcut() {
    }


    //после ошибки
    @AfterThrowing(value = "by.lobov.aop.LoggingAspect.logSaves()", throwing = "ex")
    public void withThrow(JoinPoint joinPoint, Exception ex) {
        log.error("The error was found: {}", ex.getMessage());
    }

}
