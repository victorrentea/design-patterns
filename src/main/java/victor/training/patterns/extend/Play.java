package victor.training.patterns.extend;

public class Play {

}


interface Interface {
	String met();
	int metInt(); // returns met.parseInt
}

abstract class InterfaceBase implements Interface {
	public int metInt() {
		return Integer.parseInt(met());
	}
}


class Impl1 extends InterfaceBase implements Interface {
	public String met() {
		return "99999";
	}
	
}
class Impl2 extends InterfaceBase implements Interface {
	public String met() {
		return "132131";
	}
	
}