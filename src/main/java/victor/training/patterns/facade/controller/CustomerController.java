package victor.training.patterns.facade.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.*;
import victor.training.patterns.facade.Logged;
import victor.training.patterns.facade.facade.CustomerFacade;
import victor.training.patterns.facade.facade.dto.CustomerDto;

import java.util.Arrays;

@RestController //faking it
@RequiredArgsConstructor
public class CustomerController {
   private final CustomerFacade customerFacade;

//   @Retryable(maxAttempts = 3)
   @GetMapping("{customerId}")
   public CustomerDto findById(@PathVariable long customerId) throws Exception {
      System.out.println("PE cine chem aici? " + customerFacade.getClass());

//      TransactionTemplate txTemplate;
//      txTemplate.setPropagationBehaviorName("REQUIRES_NEW");
//      txTemplate.execute(s -> {
//         logicaInTxNoua();
//      })

      return Util.measure(()->customerFacade.findById(customerId));
   }

   @PostMapping
   public void register(@RequestBody CustomerDto customerDto) {
      customerFacade.register(customerDto);
   }
}

@Order(2) // TWF:!??!?
@Slf4j
@Aspect
@Component
class LoggingAspect {
   @Around("@annotation(victor.training.patterns.facade.Logged)")
   public Object interceptForLogging(ProceedingJoinPoint pjp) throws Throwable {
      log.info("CAlling " + pjp.getSignature().getName() + " cu param " + Arrays.toString(pjp.getArgs()));
      Object result = pjp.proceed(); // las apelul sa mearga catre met tinta
      log.info("Result: " + result);
      return result;
   }
}