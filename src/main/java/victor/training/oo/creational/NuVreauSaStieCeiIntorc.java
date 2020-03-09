package victor.training.oo.creational;

import javax.xml.parsers.DocumentBuilderFactory;

public class NuVreauSaStieCeiIntorc {
    public static ICeva createCeva(int i) {
        if (i < 0) {
            return new CevaNegativ();
        } else {
            return new CevaPozitiv();
        }
    }
}
class CevaNegativ implements ICeva {}
class CevaPozitiv implements ICeva {}
interface ICeva {}
/// 
class CodClientSaracu {
    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        ICeva ceva = NuVreauSaStieCeiIntorc.createCeva(2);
        System.out.println(ceva);
        ICeva altceva = NuVreauSaStieCeiIntorc.createCeva(-2);
        System.out.println(altceva);

    }
}