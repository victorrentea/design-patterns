package victor.training.patterns.visitor;

import java.awt.image.BufferedImage;

public class Ardei {
    public static void main(String[] args) {
//        Poza poza = new VeganDecorator(new ArdeiDecorator(new PozaMancare());
    }
}


interface Poza {
    BufferedImage asPicture() ;
}
class PozaMancare implements Poza {
    @Override
    public BufferedImage asPicture() {
        return null;
    }
}
class ArdeiDecorator implements Poza {
    private final Poza pozaMancare;

    public ArdeiDecorator(Poza pozaMancare) {
        this.pozaMancare = pozaMancare;
    }

    @Override
    public BufferedImage asPicture() {
        BufferedImage originala = pozaMancare.asPicture();
        // lipeste ardeiu peste
        return null;
    }
}