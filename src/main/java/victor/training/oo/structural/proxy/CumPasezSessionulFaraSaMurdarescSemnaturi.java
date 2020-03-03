package victor.training.oo.structural.proxy;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CumPasezSessionulFaraSaMurdarescSemnaturi {
    private static final ExecutorService pool = Executors.newFixedThreadPool(1);

    static public void startAsync(Runnable runnable) {
        UserSession ses = UserSessionHolder.getCurrentSession();
        pool.submit(() -> {
            UserSessionHolder.setCurrentSession(ses);
            runnable.run();
        });
    }

    public static void main(String[] args) {
        A a = new A(new B());
        // in framework
        UserSession ses = dinMapaAiaStatic();
        UserSessionHolder.setCurrentSession(ses);


        try {
            a.m();
            startAsync(() -> a.m());
        } finally {
            //evita mem leak + user impersonation
            UserSessionHolder.setCurrentSession(null);
        }
    }

    private static UserSession dinMapaAiaStatic() {
        return new UserSession("gigel");
    }
}

class UserSessionHolder {
    private final static ThreadLocal<UserSession> threadLocalSession = ThreadLocal.withInitial(() -> null);

    public static UserSession getCurrentSession() {
        return threadLocalSession.get();
    }

    public static void setCurrentSession(UserSession userSession) {
        threadLocalSession.set(userSession);
    }
}

class A {

    private final B b;

    A(B b) {
        this.b = b;
    }

    public void m() {
        b.met();
    }

}

@Slf4j
class B {
    public void met() {
        UserSession ses = UserSessionHolder.getCurrentSession();// de unde oare Doamne?
        log.debug(ses.getUsername());
    }

}


@Data
class UserSession {
    private final String username;
    private final List<Long> competitii = new ArrayList<>();
}
