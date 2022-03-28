package victor.training.patterns.behavioral.visitor.model;

/// NOT ALLOWED TO CHANGE the data classes

// 1) The data classes come inside a JAR (DTOs, shared model)
//  and you want to make sure you've covered all the cases (concrete types)

public interface Shape {
	void accept(ShapeVisitor visitor);
}
