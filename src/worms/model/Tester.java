package worms.model;

//import static org.junit.Assert.*;

import java.util.Random;

//import worms.util.Util;

public class Tester {
	public static void main(String[] args) {
		Facade facade = new Facade();
		Random random= new Random(4);
		//final double EPS = Util.DEFAULT_EPSILON;

		// . X .
		// . w .
		// . . .
		// . . .
		// X X X
		World world = facade.createWorld(3.0, 5.0, new boolean[][] {
				{ true, false, true }, { true, true, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Worm worm = facade.createWorm(world, 1.0, 3.0,1, 0.5,
				"Test");
		//assertFalse(facade.canFall(worm));
		System.out.println(facade.getOrientation(worm));
		facade.move(worm);
		System.out.println("X is: " + worm.getCoordinateX());
		System.out.println("Y is: " + worm.getCoordinateY());
		//assertTrue(facade.canFall(worm));
//		facade.fall(worm);
//		assertEquals(1.5, facade.getX(worm), EPS);
//		assertEquals(1.5, facade.getY(worm), EPS);
	}
}
