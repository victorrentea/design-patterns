package victor.training.patterns.observer;

import java.util.ArrayList;
import java.util.List;



class OldLady {
    private final List<Listener> listeners = new ArrayList<>();

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void findOutSmth(String gossip) {
        for (Listener listener : listeners) {
            listener.notify(gossip);
        }
    }
}

interface Listener {// Topic or rabbit?
    void notify(String gossip);
}

// =============== ARCHITECTURE IS THE ART OF DRAWING LINES ===================== (boundaries) =============

class JohnFrom1stFloor implements Listener {
    @Override
    public void notify(String gossip) {
        System.out.println("John finds " + gossip);
    }
}

class JohnFrom2stFloor implements Listener {
    @Override
    public void notify(String gossip) {
        System.out.println("John2 finds " + gossip);
    }
}


public class ObserverInTheNeighborhood {
    public static void main(String[] args) {
        OldLady oldLady = new OldLady();
        oldLady.addListener(new JohnFrom1stFloor());
        oldLady.addListener(new JohnFrom2stFloor());
        oldLady.findOutSmth("Rita came at home with a Rocker🤘");
    }
}

