package worms.model;

import static org.junit.Assert.*;

import java.util.Random;

import worms.util.Util;

public class Tester {
	public static void main(String[] args) {
		Facade facade = new Facade();
		Random random= new Random(4);
		final double EPS = Util.DEFAULT_EPSILON;
		
		World world = facade.createWorld(2, 2, new boolean[][] {	{true, true, true, true},
																	{true, true, true, true},
																	{true, true, true, true},
																	{false, false, false, false}
		}, random);
		Worm worm = facade.createWorm(world, 1, 1, Math.PI / 4, 0.5,
				"Test");
		System.out.println(worm.getProjectile().getName());
		System.out.println("Projectile "+worm.getProjectile().getRadius());
		worm.selectWeapon();
		System.out.println(worm.getProjectile().getName());
		System.out.println("Projectile "+worm.getProjectile().getRadius());
		System.out.println(worm.canFall());
		worm.fall();
		System.out.println(worm.getCoordinateY());
		

	
	}
}
