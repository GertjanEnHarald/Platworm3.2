package worms.model;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class WorldTest {

	@Test
	public void addFoodAddWormCompleteTest() {
		
		boolean[][] map =  {   
				{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
				{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
				{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
				{false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false}
            };
		
		Random rand = new Random();
		
		World world = new World(20.0,20.0,map,rand);
		assertTrue(world.isPassablePixel(12, 6));
		assertFalse(world.isPassablePixel(18, 0));			
		
		assertEquals(20, world.getDimensionInPixels(true));
		assertEquals(20, world.getDimensionInPixels(false));
		
		assertTrue(world.isPassableLocation(14.5, 12.3));
		assertFalse(world.isPassableLocation(18.5, 1.99999));
		assertTrue(world.isPassableLocation(18.5, 2.0001));
		
		assertTrue(world.isPassableArea(10, 10, 0.5));
		assertFalse(world.isPassableArea(10, 3, 2));
		
		
		
		world.addWorm();
		world.addWorm();
		world.addFood();
		world.addFood();
		
		System.out.println(world.getAllWorms());
		List<Worm> worms = (List<Worm>) world.getAllWorms();
		assertEquals(2.3,worms.get(0).getCoordinateY(),0.1);
		assertEquals(2.3,worms.get(1).getCoordinateY(),0.1);
		
		
		
		List<Food> food = (List<Food>) world.getAllFood();
		System.out.println(world.getAllFood());
		assertEquals(2.2,food.get(0).getCoordinateY(),0.1);
		assertEquals(2.2,food.get(1).getCoordinateY(),0.1);
		
	}

}
