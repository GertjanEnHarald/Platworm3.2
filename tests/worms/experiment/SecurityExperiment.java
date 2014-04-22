package worms.experiment;

import java.util.Random;

import worms.model.Facade;
import worms.model.World;

public class SecurityExperiment {

	public static void main(String[] args) {
		Facade facade = new Facade();
		boolean[][] passableMap = new boolean[][] {
				{ false, false, false, false }, { true, true, true, true },
				{ true, true, true, true }, { false, false, false, false } };
		World world = facade.createWorld(1,1,passableMap,new Random(555));
	}

}
