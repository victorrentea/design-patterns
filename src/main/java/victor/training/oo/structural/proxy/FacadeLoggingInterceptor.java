//package victor.training.oo.structural.proxy;
//
//import static java.lang.annotation.ElementType.METHOD;
//import static java.lang.annotation.RetentionPolicy.RUNTIME;
//import static java.util.stream.Collectors.joining;
//
//import java.lang.annotation.Retention;
//import java.lang.annotation.Target;
//import java.lang.reflect.Method;
//import java.util.regex.Pattern;
//import java.util.stream.Stream;
//
//import javax.annotation.PostConstruct;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.bnpp.asf.common.util.ReflectionRecursiveToStringUtil;
//import com.bnpp.regliss.service.ReglissRequestContext;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//
//@Aspect
//@Component
//public class FacadeLoggingInterceptor {
//	
//	Pattern p;
//	
//	@Target(METHOD)
//	@Retention(RUNTIME)
//	public @interface NotLogged {
//	}
//	
//	@Autowired
//	private ReglissRequestContext requestContext;
//	
//	private ObjectMapper objectMapper = new ObjectMapper();
//	
//	public Logger log = LoggerFactory.getLogger(FacadeLoggingInterceptor.class);
//
//	@PostConstruct
//	public void configureMapper() {
//		if (log.isTraceEnabled()) {
//			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//		}
//	}
//	
//	@Around("execution(* *(..)) && @within(com.bnpp.asf.common.spring.Facade))")
//	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//		logBefore(joinPoint);
//		
//		Object returnedObject;
//		try {
//			returnedObject = joinPoint.proceed();
//		} catch (Exception e) {
//			log.error("Exception: {}: {}", e.getClass(), e.getMessage());
//			throw e;
//		}
//		
//		logAfter(joinPoint, returnedObject);
//		return returnedObject;
//	}
//	private boolean isNotLogged(ProceedingJoinPoint joinPoint) {
//		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//		Method method = signature.getMethod();
//		return method.getAnnotation(NotLogged.class) != null;
//	}
//
//	private void logBefore(ProceedingJoinPoint joinPoint) {		
//		if (log.isDebugEnabled() ) {
//			String separator = log.isTraceEnabled() ? "\n" : ", ";
//			String argListConcat;
//			if (isNotLogged(joinPoint)) {
//				argListConcat = "@NotLogged";
//			} else {
//				argListConcat = Stream.of(joinPoint.getArgs())
//						.map(this::toString)
//						.map(Object::toString)
//						.collect(joining(separator));
//			}
//
//			log.debug("Invoking {}.{}(..) (user:{}): {}", joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName(), requestContext.getUsername(), argListConcat);
//		}
//	}
//	
//	private String toString(Object object) {
//		try {
//			return objectMapper.writeValueAsString(object);
//		} catch (JsonProcessingException e) {
//			log.warn("Could not serialize as JSON: "+e);
//			return "<ERR>";
//		}
//	}
//	
//	private void logAfter(ProceedingJoinPoint joinPoint, Object returnedObject) {
//		if (log.isDebugEnabled()) {
//			log.debug("Return from {}: {}", joinPoint.getSignature().getName(), 
//					isNotLogged(joinPoint) ?"": ReflectionRecursiveToStringUtil.recursiveDescribe(returnedObject));
//		}
//	}
//}
