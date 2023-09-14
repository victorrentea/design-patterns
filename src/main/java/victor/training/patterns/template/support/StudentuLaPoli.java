package victor.training.patterns.template.support;

class Mama {
  /*open virtual */public boolean daVoieFiuluiSaMeargaLaBar() {
    return false;
  }

  public void dormLinistita() {
    if (daVoieFiuluiSaMeargaLaBar() == false) {
      System.out.printf("zZZZ");
    }
  }

  public void canaCuApa() {} // hook methods. doar unele din subtipuri vor
  // #siele sa puna ceva
}

public class StudentuLaPoli extends Mama{
  public void mergLaBar() {
    if (!daVoieFiuluiSaMeargaLaBar()) {
      throw new IllegalArgumentException("n-am bani");
    }
  }

  @Override
  public boolean daVoieFiuluiSaMeargaLaBar() {
    return true;
  }
}
