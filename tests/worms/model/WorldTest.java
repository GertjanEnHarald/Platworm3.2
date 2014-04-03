package worms.model;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class WorldTest {

	@Test
	public void test() {
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
		assertTrue(world.isPassableLocation(2, 19));
		assertFalse(world.isPassableLocation(18.5, 1.99999));
		assertTrue(world.isPassableLocation(18.5, 2.0001));
		assertTrue(world.isPassableArea(10, 10, 0.5));
		assertTrue(world.isPassableArea(10, 2.5, 0.4));
		assertFalse(world.isPassableArea(10, 2.5,0.6));
		assertFalse(world.isPassableArea(10, 3, 2));
		assertFalse(world.isPassableArea(10, 2.05, 1));
		assertTrue(world.isPassableArea(10, 3.95, 1));
		assertTrue(world.isPassableArea(10, 3.05, 1));
		assertFalse(world.isPassableArea(10, 3.05, 1.5));
		assertTrue(world.isAdjacent(10.0, 4.05, 2));
		assertFalse(world.isAdjacent(10.0, 3.15, 1));
		double[] lst = {10.0,3.0};
		assertArrayEquals(lst, world.getRandomAdjacentLocation(1.0), 0.1);
		
	}

}
