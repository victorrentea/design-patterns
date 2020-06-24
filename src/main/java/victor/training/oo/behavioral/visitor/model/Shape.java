package victor.training.oo.behavioral.visitor.model;

import victor.training.oo.behavioral.visitor.ShapeVisitor;

public interface Shape {
	void accept(ShapeVisitor visitor);


	double area();

//	String drawInAsciiArt(); // implem acestei functii in subtipuri va necesita 80% (20-100linii) de cod comun
}
