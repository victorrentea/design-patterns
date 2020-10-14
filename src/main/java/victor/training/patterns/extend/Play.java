package victor.training.patterns.extend;

public class Play {

}


interface Interface {
	String met();
	default int metInt() {
		return Integer.parseInt(met());
	}
}


class Impl1 implements Interface {
	public String met() {
		return "99999";
	}
	
}
class Impl2  implements Interface {
	public String met() {
		return "132131";
	}
	
}