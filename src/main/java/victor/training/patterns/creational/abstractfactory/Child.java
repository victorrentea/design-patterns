package victor.training.patterns.creational.abstractfactory;

import victor.training.patterns.creational.abstractfactory.spi.BlockFactory;
import victor.training.patterns.creational.abstractfactory.spi.Board;
import victor.training.patterns.creational.abstractfactory.spi.Cube;
import victor.training.patterns.stuff.ThreadUtils;

public class Child {
	public void playWith(BlockFactory blockFactory) {
		Board board = blockFactory.createBoard();
		Cube lastCube = blockFactory.createCube();
		System.out.println("Placing first " + lastCube + " on " + board);
		lastCube.putOn(board);
		for (int i = 1; i < 10; i++) {
			Cube cube = blockFactory.createCube();
			System.out.println("Stacking cube " + (i+1) + " " + cube + " onto  " + lastCube);
			ThreadUtils.sleepq(200);
			cube.stackOnto(lastCube);
			lastCube = cube;
		}
		System.out.println("Done");
	}
}
