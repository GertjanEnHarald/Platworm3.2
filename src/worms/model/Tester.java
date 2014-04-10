package worms.model;

import static org.junit.Assert.*;

import java.util.Random;

import worms.util.Util;

public class Tester {
	public static void main(String[] args) {
		Facade facade = new Facade();
		Random random= new Random(4);
		final double EPS = Util.DEFAULT_EPSILON;

		// . X .
		// . w .
		// . . .
		// X X X
		World world = facade.createWorld(3.0, 4.0, new boolean[][] {
				{ true, true, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Worm worm = facade.createWorm(world, 1.5, 2.5, Math.PI / 4, 0.5,
				"Test");
		System.out.println(worm.getJumpTime());
		System.out.println(worm.getJumpRealTimeInAir());
		
		
//		assertFalse(facade.canFall(worm));
//		worm.move(2);
//		System.out.println("X is: " + worm.getCoordinateX());
//		System.out.println("Y is: " + worm.getCoordinateY());
//		assertTrue(facade.canFall(worm));
//		facade.fall(worm);
//		System.out.println("X is: " + worm.getCoordinateX());
//		System.out.println("Y is: " + worm.getCoordinateY());
//		assertEquals(1.5, facade.getX(worm), EPS);
//		assertEquals(1.5, facade.getY(worm), EPS);		
	}
}
