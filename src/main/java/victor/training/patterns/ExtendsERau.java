package victor.training.patterns;

public class ExtendsERau {
public static void main(String[] args) {
	new Copil().mersLaBar();
}
}

class Tata { // poate depinde de copil !!! cand copilul face @Override la metode din parinte.

	public void portofel() {
		if (!mamaDaVoie()) {
			throw new RuntimeException("Nu ti-l dau");
		}
	}

	public boolean mamaDaVoie() {
		return false;
	} 
}

class Copil extends Tata { // depinde de parinte
	public void mersLaBar() {
		portofel();
		System.out.println("Beu");
	}

	@Override
	public boolean mamaDaVoie() {  // NICIODATA sa nu suprascrii metode concrete din subclasa
		return true;
	}
}