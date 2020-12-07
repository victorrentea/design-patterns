package victor.training.patterns.creational.factory.abstractfactory;

import victor.training.patterns.creational.factory.abstractfactory.spi.BlockFactory;
import victor.training.patterns.creational.factory.abstractfactory.spi.Board;
import victor.training.patterns.creational.factory.abstractfactory.spi.Cube;
import victor.training.patterns.stuff.ThreadUtils;

public class Child {
	public void playWith(BlockFactory box) {
		Board board = box.createBoard();
		Cube lastCube = box.createCube();
		System.out.println("Placing first " + lastCube + " on " + board);
		lastCube.putOn(board);
		for (int i = 1; i < 10; i++) {
			Cube cube = box.createCube();
			System.out.println("Stacking cube " + (i+1) + " " + cube + " onto  " + lastCube);
			ThreadUtils.sleepq(200);
			cube.stackOnto(lastCube);
			lastCube = cube;
		}
		System.out.println("Done");
	}
}
