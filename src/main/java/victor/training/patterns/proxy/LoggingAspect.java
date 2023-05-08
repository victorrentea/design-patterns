package victor.training.patterns.proxy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    private final ObjectMapper jackson = new ObjectMapper();

    @PostConstruct
    public void configureMapper() {
        if (log.isTraceEnabled()) {
            log.trace("JSON serialization will be indented");
            jackson.enable(SerializationFeature.INDENT_OUTPUT);
        }
    }

    @Around("@within(victor.training.patterns.proxy.Facade))") // all methods inside classes annotated with @Facade
//    @Around("@annotation(victor.training.patterns.proxy.LoggedMethod))") // all methods annotated with @LoggedMethod
//    @Around("execution(* org.springframework.data.jpa.repository.JpaRepository+.*(..))") // all subtypes of JpaRepository
//    @Around("execution(* ..*.get*(..))") // all methods starting with "get" everywhere!! = naming convention = dangerous
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        if (log.isDebugEnabled()) {
            String methodName = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();
            String currentUsername = "SecurityContextHolder.getContext().getName()"; // TODO
            String argListConcat = Stream.of(joinPoint.getArgs()).map(this::jsonify).collect(joining(","));
            log.debug("Invoking {}(..) (user:{}): {}", methodName, currentUsername, argListConcat);
        }

        try {
            Object returnedObject = joinPoint.proceed(); // allow the call to propagate to original (intercepted) method

            if (log.isDebugEnabled()) {
                log.debug("Returned value: {}", jsonify(returnedObject));
            }
            return returnedObject;
        } catch (Exception e) {
            log.error("Threw exception {}: {}", e.getClass(), e.getMessage());
            throw e;
        }
    }

    private String jsonify(Object object) {
        if (object == null) {
            return "<null>";
        } else if (object instanceof OutputStream) {
            return "<OutputStream>";
        } else if (object instanceof InputStream) {
            return "<InputStream>";
        } else {
            try {
                return jackson.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                log.warn("Could not serialize as JSON (stacktrace on TRACE): " + e);
                log.trace("Cannot serialize value: " + e, e);
                return "<JSONERROR>";
            }
        }
    }

}

