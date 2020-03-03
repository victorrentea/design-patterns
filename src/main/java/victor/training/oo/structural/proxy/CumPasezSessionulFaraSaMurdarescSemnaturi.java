package victor.training.oo.structural.proxy;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class CumPasezSessionulFaraSaMurdarescSemnaturi {
    public static void main(String[] args) {
        A a = new A(new B());
        // in framework
        UserSession ses = dinMapaAiaStatic();
        UserSessionHolder.setCurrentSession(ses);


        // ses=getCurrSession(); executor.submit(() -> {restoreSessionONThread(ses);task.run();}

        try {
            a.m();
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
class B {
    public void met() {
        UserSession ses = UserSessionHolder.getCurrentSession();// de unde oare Doamne?
        System.out.println(ses.getUsername());
    }

}
















@Data
class UserSession{
    private final String username;
    private final List<Long> competitii = new ArrayList<>();
}
