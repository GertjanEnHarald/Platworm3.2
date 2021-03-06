/**
 * Test the class Worm.java
 */

package worms.model;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
//import org.junit.Before;
import org.junit.Test;

import worms.util.Util;

public class Tests {
	private static final double EPS = Util.DEFAULT_EPSILON;
	private Random random;
	private World world;
	private World world2;

	@Before
	public void setup() {
		boolean[][] map = {
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ true, true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true,
						true, true, true, true, true, true, true, true, true },
				{ false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false },
				{ false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false },
				{ false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false },
				{ false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false },
				{ false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false },
				{ false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false },
				{ false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false },
				{ false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false },
				{ false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false },
				{ false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false, false, false, false, false,
						false, false, false, false } };
		this.random = new Random(7357);
		this.world = new World(20.0, 20.0, map, random);
	}

	@Before
	public void setup2() {
		boolean[][] map2 = { 	{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false,false, false, false, false, false, false, false, false, false, false}						
};
		this.world2 = new World(20.0, 20.0, map2, random);
	}
	
	@Test
	public void testDensity() {
		Worm testWorm = new Worm(0, 0, 0, 1, "James O'Hare", true, world);
		assertEquals(testWorm.getDensity(), 1062, EPS);
	}

	@Test
	public void testGravity() {
		Worm testWorm = new Worm(0, 0, 0, 1, "James O'Hare", true, world);
		assertEquals(9.80665, testWorm.getWorld().getGravity(), EPS);
	}

	@Test
	public void testConstructorLegalCase1() throws ModelException {
		Worm testWorm = new Worm(0, 0, 0, 1, "James O'Hare", true, world);
		assertEquals(0, testWorm.getCoordinateX(), EPS);
		assertEquals(0, testWorm.getCoordinateY(), EPS);
		assertEquals(0, testWorm.getDirection(), EPS);
		assertEquals(1, testWorm.getRadius(), EPS);
		assertEquals("James O'Hare", testWorm.getName());
	}

	@Test
	public void testConstructorLegalCase2() throws ModelException {
		Worm testWorm = new Worm(5, 1, -3 * Math.PI, 1.9, "Test \' \" ", true,
				world);
		assertEquals(5, testWorm.getCoordinateX(), EPS);
		assertEquals(1, testWorm.getCoordinateY(), EPS);
		assertEquals(Math.PI, testWorm.getDirection(), EPS);
		assertEquals(1.9, testWorm.getRadius(), EPS);
		assertEquals("Test \' \" ", testWorm.getName());
	}

	@Test
	public void testConstructorLegalCase3() throws ModelException {
		Worm testWorm = new Worm(2, 10, 3 * Math.PI, 20, "James 007", true,
				world);
		assertEquals(2, testWorm.getCoordinateX(), EPS);
		assertEquals(10, testWorm.getCoordinateY(), EPS);
		assertEquals(Math.PI, testWorm.getDirection(), EPS);
		assertEquals(20, testWorm.getRadius(), EPS);
		assertEquals("James 007", testWorm.getName());
	}

	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseCoordinateXIsNan()
			throws ModelException {
		new Worm(Double.NaN, 0, 0, 1, "Bob", true, world);
	}

	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseCoordinateYIsNan()
			throws ModelException {
		new Worm(0, Double.NaN, 0, 1, "Bob", true, world);
	}

	@Test
	public void testConstructorIllegalCaseCoordinateXIsNotInMap()
			throws ModelException {
		Worm testWorm = new Worm(25, 0, 0, 1, "Bob", true, world);
		assertFalse(testWorm.getStatus());
	}

	@Test
	public void testConstructorIllegalCaseCoordinateYIsNotInMap()
			throws ModelException {
		Worm testWorm = new Worm(2, -5, 0, 1, "Bob", true, world);
		assertFalse(testWorm.getStatus());
	}

	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseRadiusSmallerThanMinimum()
			throws ModelException {
		new Worm(0, 0, 0, 0.1, "Bob", true, world);
	}

	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseRadiusIsNan() throws ModelException {
		new Worm(0, 0, 0, Double.NaN, "Bob", true, world);
	}

	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseRadiusIsMaxInteger()
			throws ModelException {
		new Worm(0, 0, 0, Integer.MAX_VALUE, "Bob", true, world);
	}

	@Test
	public void testChangeRadiusLegalCase() {
		Worm testWorm = new Worm(10.0, 5.0, Math.PI, 1, "Bob", true, world);
		testWorm.setRadius(50);
		assertEquals(testWorm.getRadius(), 50.0, EPS);
	}

	@Test(expected = ModelException.class)
	public void testChangeRadiusIllegalCase() {
		Worm testWorm = new Worm(10.0, 10.0, Math.PI, 1, "Bob", true, world);
		testWorm.setRadius(Integer.MAX_VALUE);
	}

	@Test
	public void testConstructorLegalCaseMass() throws ModelException {
		Worm testWorm = new Worm(10.0, 8.0, Math.PI, 1.0, "Bob", true, world);
		assertEquals(1062 * 4 / 3 * Math.PI, testWorm.getMass(), EPS);
	}

	@Test
	public void testConstructorLegalCaseActionPoints() throws ModelException {
		Worm testWorm = new Worm(10.0, 7.0, Math.PI, 1.0, "Bob", true, world);
		world.addAsGameObject(testWorm);
		assertEquals(testWorm.getMaximumActionPoints(),
				testWorm.getActionPoints(), EPS);
		assertEquals(4448, testWorm.getActionPoints());
	}

	@Test
	public void testConstructorLegalCaseHitPoints() throws ModelException {
		Worm testWorm = new Worm(10.0, 7.0, Math.PI, 1.0, "Bob", true, world);
		world.addAsGameObject(testWorm);
		assertEquals(testWorm.getMaximumHitPoints(), testWorm.getHitPoints(),
				EPS);
		assertEquals(4448, testWorm.getHitPoints());
	}

	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseNameSingleLetter()
			throws ModelException {
		new Worm(10.0, 20.0, Math.PI, 1.0, "J", true, world);
	}

	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseNameNoStartingCapital()
			throws ModelException {
		new Worm(10.0, 1.0, Math.PI, 1.0, "james", true, world);
	}

	@Test(expected = ModelException.class)
	public void testConstructorIllegalCaseNameIllegalSymbol()
			throws ModelException {
		new Worm(10.0, 5.0, Math.PI, 1.0, "J*", true, world);
	}

	@Test
	public void testConstructorLegalCaseNameNumber() throws ModelException {
		Worm testWorm = new Worm(10.0, 5.0, Math.PI, 1.0, "James 5", true,
				world);
		assertEquals("James 5", testWorm.getName());
	}

	@Test
	public void testTurnLegalCase1() {
		Worm testWorm = new Worm(10.0, 10.0, Math.PI, 1, "Bob", true, world);
		testWorm.turn(Math.PI / 4);
		assertEquals(5 * Math.PI / 4, testWorm.getDirection(), EPS);
		int actionPoints = (int) (testWorm.getMaximumActionPoints() - (	(60 * Math.PI / 4) / (2 * Math.PI)));
		assertEquals(actionPoints, testWorm.getActionPoints());
		assertEquals(testWorm.getDirection(), testWorm.getProjectile().getDirection(),EPS);
	}

	@Test
	public void testTurnLegalCase2() {
		Worm testWorm = new Worm(10.0, 5.0, Math.PI, 1, "Bob", true, world);
		testWorm.turn(-9 * Math.PI / 2);
		assertEquals(Math.PI / 2, testWorm.getDirection(), EPS);
		int actionPoints = (int) (testWorm.getMaximumActionPoints() - (Math
				.round((60 * Math.PI / 2) / (2 * Math.PI))));
		assertEquals(actionPoints, testWorm.getActionPoints());
	}

	@Test
	public void testAddWormToTeam() {
		Worm testWorm = new Worm(12.0, 4.0, Math.PI / 4, 1, "Bob", true, world);
		world.addAsGameObject(testWorm);
		Team winners = new Team("Winners");
		testWorm.joinTeam(winners);
		winners.addWorm(testWorm);
		assertEquals(testWorm.getTeam(),winners);
		assertTrue(winners.getLivingWorms().contains(testWorm));
		assertTrue(testWorm.hasProperTeam());
	}

	@Test
	public void testRemoveWormFromTeam() {
		Worm testWorm = new Worm(12.0, 4.0, Math.PI / 4, 1, "Bob", true, world);
		world.addAsGameObject(testWorm);
		Team winners = new Team("Winners");
		testWorm.joinTeam(winners);
		winners.addWorm(testWorm);
		assertEquals(testWorm.getTeam(),winners);
		assertTrue(winners.getLivingWorms().contains(testWorm));
		winners.removeFromTeam(testWorm);
		assertFalse(winners.getLivingWorms().contains(testWorm));
	}
	
	@Test(expected = ModelException.class)
	public void testIllegalCaseAddWormToTeamGameAlreadyStarted() {
		Worm testWorm = new Worm(12.0, 4.0, Math.PI / 4, 1, "Bob", true, world);
		world.addAsGameObject(testWorm);
		Team winners = new Team("Winners");
		world.startGame();
		testWorm.joinTeam(winners);
	}

	@Test
	public void testTerminateWorm() {
		Worm testWorm = new Worm(12.0, 4.0, Math.PI / 4, 1, "Bob", true, world);
		world.addAsGameObject(testWorm);
		Team winners = new Team("Winners");
		testWorm.joinTeam(winners);
		winners.addWorm(testWorm);
		testWorm.terminate();
		assertFalse(testWorm.getStatus());
		assertFalse(world.getAllWorms().contains(testWorm));
		assertEquals(null, testWorm.getWorld());
		assertFalse(winners.getLivingWorms().contains(testWorm));
		assertEquals(null, testWorm.getTeam());
	}
	
	@Test
	public void testIsAlive() {
		Worm testWorm = new Worm(12.0, 4.0, Math.PI / 4, 1, "Bob", true, world);
		world.addAsGameObject(testWorm);
		testWorm.setHitPoints(-1);
		assertFalse(testWorm.isAlive());
	}

	@Test
	public void testAddWormToWorld() {
		Worm testWorm = new Worm(12.0, 4.0, Math.PI / 4, 1, "Bob", true, world);
		world.addAsGameObject(testWorm);
		assertTrue(world.getAllWorms().contains(testWorm));
		assertEquals(world, testWorm.getWorld());
	}
	
	@Test
	public void testSelectProjectile() {
		Worm testWorm = new Worm(12.0, 4.0, Math.PI / 4, 1, "Bob", true, world);
		world.addAsGameObject(testWorm);
		assertTrue(testWorm.getProjectile() instanceof Rifle);
		int weaponNumber = testWorm.getCurrentWeaponNumber();
		testWorm.selectWeapon();
		assertTrue(testWorm.getProjectile() instanceof Bazooka);
		assertEquals(testWorm.getCurrentWeaponNumber(), (weaponNumber+1)%Worm.getNumberOfProjectiles());
	}
	
	@Test
	public void testShoot() {
		Worm testWorm = new Worm(12.0, 4.0, Math.PI / 4, 1, "Bob", true, world);
		world.addAsGameObject(testWorm);
		testWorm.shoot(50);
		assertEquals(testWorm.getProjectile().getYield(), 50);
		assertEquals(testWorm.getActionPoints(), testWorm.getMaximumActionPoints()-testWorm.getProjectile().getCostActionPoints());
	}

	@Test
	public void testFall() {
		Worm testWorm = new Worm(12.0, 10.0, Math.PI / 4, 1, "Bob", true, world);
		world.addAsGameObject(testWorm);
		int hitPoints = testWorm.getHitPoints();
		testWorm.fall();
		assertEquals(testWorm.getCoordinateX(),12.0,EPS);
		assertEquals(testWorm.getCoordinateY(), 2.0+testWorm.getRadius(),testWorm.getRadius()*0.1);
		assertTrue(world.isAdjacent(testWorm.getCoordinateX(), testWorm.getCoordinateY(), testWorm.getRadius()));
		assertEquals(testWorm.getHitPoints(),hitPoints - 3*((int) Math.round((10.0-testWorm.getCoordinateY()))));
	}
	
	
	@Test
	public void testMoveVertical(){
		Worm testWorm = new Worm(12.0, 3.05, Math.PI/2.0, 1, "Bob", true, world);
		testWorm.move();
		assertEquals(testWorm.getCoordinateY(), 4.05,testWorm.getRadius()*0.05);
		assertEquals(testWorm.getCoordinateX(), 12.0,EPS);
		testWorm.fall();
		assertEquals(testWorm.getCoordinateY(), 3.05,testWorm.getRadius()*0.05);
		assertEquals(testWorm.getCoordinateX(), 12.0,EPS);
	}
	
	
	@Test
	public void testMoveHorizontal(){
		Worm testWorm = new Worm(12.0, 3.05,0.0, 1, "Bob", true, world);
		testWorm.move();
		assertEquals(testWorm.getCoordinateY(), 3.05,EPS);
		assertEquals(testWorm.getCoordinateX(), 13.0,EPS);
	}
	
	@Test
	public void testMoveToTheLeftAndUp(){
		 Worm testWorm = new Worm(12.0, 3.0,Math.PI/2.0 + Math.PI/5.0, 1, "Bob", true, world);
		 testWorm.move();
		 testWorm.fall();
		 assertEquals(testWorm.getCoordinateY(), 3.0,testWorm.getRadius()*0.1);
		 assertEquals(testWorm.getCoordinateX(), 11.25,0.3);
	}
	
	 @Test(expected=ModelException.class) 
	 public void testMoveIllegalCaseCannotMoveInsufficientActionPoints() throws ModelException {
		 Worm testWorm = new Worm(12.0, 3.05, Math.PI/2.0+Math.PI/8.0, 1, "Bob", true, world);
		 testWorm.setActionPoints(0);
		 testWorm.move();
	 }
	
	 @Test(expected=ModelException.class) 
	 public void testMoveIllegalCaseCannotMoveIllegalDirection() throws ModelException {
		 Worm testWorm = new Worm(12.0, 3.0, -Math.PI/2.0, 1, "Bob", true, world);
		 testWorm.move();
	 }
	
	
	@Test
	public void testJumpLegalCaseFlatJump() {
		Worm testWorm = new Worm(12.0, 4.0, Math.PI / 4, 1, "Bob", true, world);
		world.addAsGameObject(testWorm);
		Worm testWorm2 = new Worm(12.0, 4.0, Math.PI / 4, 1, "Bob", true, world);
		world.addAsGameObject(testWorm2);
		testWorm.fall();
		testWorm.jump(Math.pow(10, -4));

		assertEquals(
				testWorm.getCoordinateX(),
				12.0 + (Math.pow(
						(0.5 * (5 * testWorm.getMaximumActionPoints() + testWorm.getMass() * 9.80665)) / testWorm.getMass(), 2)
						* Math.sin(2 * testWorm.getDirection()) / 9.80665),testWorm.getRadius()*0.1);
		assertEquals(testWorm.getActionPoints(), 0);
		assertEquals(testWorm.getProjectile().getCoordinateX(),
				testWorm.getCoordinateX() + testWorm.getRadius()*Math.cos(testWorm.getDirection()),testWorm.getRadius()*0.1);
		assertEquals(testWorm.getProjectile().getCoordinateY(),
				testWorm.getCoordinateY() + testWorm.getRadius()*Math.sin(testWorm.getDirection()),testWorm.getRadius()*0.1);
	}
	
	@Test
	public void testJumpLegalCaseToALowerLevel() {
		Worm testWorm = new Worm(8.0, 11.0, Math.PI / 4, 1, "Bob", true, world2);
		/* double[] top = testWorm.getJumpStep(testWorm.getJumpTime()/2.0);
		 * double[] flat = testWorm.getJumpStep(testWorm.getJumpTime());
		 * System.out.println("Top: "+top[0]+" "+top[1]);
		 * System.out.println("Flat: "+flat[0]+" "+flat[1]);
		*/

		assertFalse(testWorm.canFall());
		testWorm.jump(Math.pow(10, -4));
		
		/* Make a parabola to calculate the x coordinate of the worm, where y = 3.0
		 * y = a*x� + b*x + c
		 * 3 points are known: 	- start: (8.0, 11.0)
		 * 						- highest point: testWorm.getJumpStep(testWorm.getJumpTime()/2.0) 
		 * 											= (10.794282477568133, 12.397141238784068)
		 * 						- landing point, if flat jump: testWorm.getJumpStep(testWorm.getJumpTime())
		 * 											= (13.588564955136267, 11.0)
		 * a =  -0.1789, b =  3.8630, c = -8.4520
		 * So: a*x� + b*x + c - 3.0 = 0.0 if x = 18.04111175
		 */
		
		assertEquals(testWorm.getCoordinateX(),18.04111175,testWorm.getRadius()*0.1);
		assertEquals(testWorm.getCoordinateY(),3.0,testWorm.getRadius()*0.1);
	}
	
	@Test
	public void testJumpLegalCaseAgainstWall() {
		Worm testWorm = new Worm(12.794288248, 3.0, 3*Math.PI / 4, 1, "Bob", true, world2);
		testWorm.jump(Math.pow(10, -4));
		/*
		 * The worm jumps 2.397141238784068 high, until it hits the wall (derived from previous test).
		 * The worm will have an y coordinate equal to 10.0 + 1.0 = 11.0 (distance of wall + radius).
		 */
		assertEquals(testWorm.getCoordinateX(),11.0,testWorm.getRadius()*0.2);
		assertEquals(testWorm.getCoordinateY(),2.397141238784068+2.0,testWorm.getRadius()*0.2);
	}

	@Test(expected = ModelException.class)
	public void testJumpIllegalCaseInvalidActionPoints() throws ModelException {
		Worm testWorm = new Worm(10.0, 4.0, Math.PI, 1, "Bob", true, world);
		testWorm.fall();
		testWorm.jump(Math.pow(10, -4));
		testWorm.jump(Math.pow(10, -4));
	}

}
