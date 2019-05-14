package victor.training.oo;

import java.util.HashSet;
import java.util.Set;

public class CumPotiSaPierziOValoareInSet {
	public static void main(String[] args) {

		Set<Copil> copiiiMei = new HashSet<>();
		Copil emma = new Copil("Emma");
		copiiiMei.add(emma);
		System.out.println("Hash(emma):" + emma.hashCode());
		
		System.out.println(copiiiMei.contains(emma));
		emma.setName("Emma-Simona");

		System.out.println("Hash(emma):" + emma.hashCode());
		System.out.println(copiiiMei.contains(emma));
		
	}
}
class Copil {
	private String name;

	public Copil(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Copil other = (Copil) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}