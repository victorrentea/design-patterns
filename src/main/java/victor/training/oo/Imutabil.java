package victor.training.oo;

import static java.util.Collections.unmodifiableCollection;
import static java.util.Collections.unmodifiableList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Imutabil {
	private final String x;
	private final List<String> slist;
	private final AltaClasa c;

	public Imutabil(String x, List<String> slist, AltaClasa c) {
		this.x = x;
		this.slist = new ArrayList<>(slist);
		this.c = c;
	}
	
	public String getX() {
		return x;
	}
	public Imutabil withX(String altX) {
		return new Imutabil(altX, slist, c);
	}

	public List<String> getSlist() {
		return unmodifiableList(slist);
	}
	
	public AltaClasa getC() {
		return c;
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		//		new Imutabil("a", new AltaClasa()).getSlist().add("a");
		Imutabil i = new Imutabil("a", list, new AltaClasa());
//		i.getC().setBani(BigDecimal.ZERO);
		Imutabil altI = i.withX("b");
		
		
		list.add("Tzeapa!!");
	}
}
class AltaClasa { // TODO ar trebui sa o fac si pe asta imutabila
	private BigDecimal bani;

	public BigDecimal getBani() {
		return bani;
	}

	public void setBani(BigDecimal bani) throws Exception {
		this.bani = bani;
	}
	
}
