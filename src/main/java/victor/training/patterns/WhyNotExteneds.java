package victor.training.patterns;

public class WhyNotExteneds {


}
class Mother {
    public void creditCard() {
        if (!motherGivesMoney()) {
            throw new IllegalArgumentException();
        }
    }
    public boolean motherGivesMoney() {
        return false;
    }
}

class Student extends Mother {

    public void drink() {
        if (motherGivesMoney()) {
            creditCard();
        }
    }

    @Override
    public boolean motherGivesMoney() { // Java sucks,. in kotlin, scala, C#, c++ all methods are by default final.
        return true;
    }
}
