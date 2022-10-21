package victor.training.patterns.proxy;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Service
@Transactional
@Retention(RetentionPolicy.RUNTIME)
public @interface Facade {
}