package victor.training.patterns.extend;

public class Play {

}


class BetterInterface {
	private Interface interf;
	
	public String met() {
		return interf.met();
	}
	public int metInt() {
		return Integer.parseInt(interf.met());
	}
}
interface Interface {
	String met();
}


class Impl1  implements Interface {
	public String met() {
		return "99999";
	}
	
}
class Impl2 implements Interface {
	public String met() {
		return "132131";
	}
	
}