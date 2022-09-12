package victor.training.patterns;

public class Student extends Mama {
    public void bea() {
        if (mamaDaVoie()) {
            portofel();
        }
    }

    protected boolean mamaDaVoie() {
        return true;
    }
}

class Mama {
    protected boolean mamaDaVoie() {
        throw new RuntimeException("Method not implemented");
    }
    public void portofel() {
        System.out.println("bani");
    }

    public void lasCophiluLaNeversee() {
        if (mamaDaVoie()) {
//            ....
        }
    }
}