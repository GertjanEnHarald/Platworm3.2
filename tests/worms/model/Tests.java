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
	private static final double accuracyMapDimensions = 20.0 / 100;
	private Random random;
	private World world;

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

	@Test
	public void testDensity() {
		Worm testWorm = new Worm(0, 0, 0, 1, "James O'Hare", true, world);
		assertEquals(testWorm.getDensity(), 1062, EPS);
	}

	@Test
	public void testGravity() {
		assertEquals(9.80665, Worm.getGravity(), EPS);
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
		int actionPoints = (int) (testWorm.getMaximumActionPoints() - (Math
				.round((60 * Math.PI / 4) / (2 * Math.PI))));
		assertEquals(actionPoints, testWorm.getActionPoints());
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
		assertEquals(testWorm.getCoordinateY(), 2.0+testWorm.getRadius(), EPS);
		assertTrue(world.isAdjacent(testWorm.getCoordinateX(), testWorm.getCoordinateY(), testWorm.getRadius()));
		assertEquals(testWorm.getHitPoints(),hitPoints - 3*((int) Math.round((10.0-testWorm.getCoordinateY()))));
	}
	
	/*
	 * TODO test move
	 * 
	 * @Test public void testMoveLegalCase1(){ Worm testWorm = new
	 * Worm(10.0,5.0,Math.PI*3/5,1,"Bob",true,world); testWorm.move();
	 * assertEquals(testWorm.getCoordinateX(),
	 * 10+0.5*testWorm.getRadius()*Math.cos(Math.PI*3/5), EPS);
	 * assertEquals(testWorm.getCoordinateY(),
	 * 5+0.5*testWorm.getRadius()*Math.sin(Math.PI*3/5), EPS);
	 * assertEquals(testWorm.getActionPoints(),
	 * testWorm.getMaximumActionPoints()-((int) (Math.abs(Math.cos(Math.PI*3/5))
	 * + 4*Math.abs(Math.sin(Math.PI*3/5))))); }
	 * 
	 * @Test(expected=ModelException.class) public void
	 * testMoveIllegalCaseCannotMove() throws ModelException { Worm testWorm =
	 * new Worm(10.0,5.0,0,1,"Bob",true,world); while(testWorm.canMove(1,
	 * testWorm.getDirection())) { testWorm.move(); } }
	 */

	@Test
	public void testJumpLegalCase() {
		Worm testWorm = new Worm(12.0, 4.0, Math.PI / 4, 1, "Bob", true, world);
		world.addAsGameObject(testWorm);
		testWorm.fall();
		testWorm.jump(Math.pow(10, -4));
		assertEquals(
				testWorm.getCoordinateX(),
				12.0 + (Math.pow(
						(0.5 * (5 * testWorm.getMaximumActionPoints() + testWorm.getMass() * 9.80665)) / testWorm.getMass(), 2)
						* Math.sin(2 * testWorm.getDirection()) / 9.80665),accuracyMapDimensions);
		assertEquals(testWorm.getActionPoints(), 0);
	}

	@Test(expected = ModelException.class)
	public void testJumpIllegalCaseInvalidActionPoints() throws ModelException {
		Worm testWorm = new Worm(10.0, 4.0, Math.PI, 1, "Bob", true, world);
		testWorm.fall();
		testWorm.jump(Math.pow(10, -4));
		testWorm.jump(Math.pow(10, -4));
	}

}
