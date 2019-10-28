//package victor.training.oo.structural.adapter.domain;
//
//import static java.util.Collections.unmodifiableList;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public interface CumSaTiPierziUnCopilInAuchan {
//
//	public static void main(String[] args) {
//		Set<Copil> copii = new HashSet<>();
//		
//		Copil childOne = new Copil("Emma");
//		copii.add(childOne);
//		
//		System.out.println("hash(copil)=" + childOne.hashCode());
//		System.out.println(copii.contains(childOne));
//		
//		// adolescenta
//		
//		childOne.setName("Emma-Simona");
//		
//		System.out.println("hash(copil)=" + childOne.hashCode());
//		System.out.println(copii.contains(childOne));
//	
//	}
//}
//
//
//class Copil  {
//	private final String name;
//
//	public Copil(String name) {
//		this.name = name;
//	}
//	
//	public String getName() {
//		return name;
//	}
//	
////	public void setName(String name) {
////		this.name = name;
////	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Copil other = (Copil) obj;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		return true;
//	}
//}
//
//
//
//class PlayStack  {
//	public static void main(String[] args) {
//		Stack stack = new Stack();
//		
//		Stack newStack = stack.push("a");
//		List<String> list = new ArrayList<String>();
//		PlayStack playStack = new PlayStack(list);
//		
//		list.add("tzeapa");
//		
//		
//		cache.add("key", list);
//		list.add("si pasta");
//	}
//	
//	
//	public String m() {
//
//		StringBuilder sb = new StringBuilder();
//		
//		sb.append("aaa");
//		
//		return	sb.toString();
//	}
//	
//	
//	private List<String> peCamp;
//	
//	public PlayStack(List<String> a) {
//		if (a == null) throw new IllegalArgumentException();
//		peCamp = new ArrayList<>(a);
////		a.sort(comp);
//	}
//
//	public List<String> getPeCamp() {
//		return unmodifiableList(peCamp);
//	}
//
//	
//	
//}
//
//
// class Stack {
//	  private final int size;
//	  private final String[] items;
//	  public Stack(int maxSize, int size) {
//		  this.size = size;
//		  items = new String[maxSize];
//		  
//	  }
//	  public Stack(String contents, int size) {
//		  this.size = size;
//		items = contents;
//		  
//	  }
//	  public Stack push(String item) {
//	    size++;
//	    if (size > items.length) {
//	      throw new RuntimeException("stack overflow");
//	    }
//	    items[size] = item;
//	    arr = new String[length + 1]; // copy all ontent, add new item
//	    return Stack(arr ,size + 1);
//	  }
//	}