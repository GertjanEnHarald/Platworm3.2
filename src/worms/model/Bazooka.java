package worms.model;

import be.kuleuven.cs.som.annotate.*;

public class Bazooka extends Projectile {
	

	public Bazooka(double coordinateX, double coordinateY, boolean isActive,
			double radius, World world) throws ModelException {
		super(coordinateX, coordinateY, isActive, radius, world, 300,
				80, 50);
	}

	/**
	 * Returns an interval with possible forces in Newton 
	 * at which projectiles from a bazooka are propelled.
	 * 
	 * 
	 * @return	Returns the force.
	 * 			| result == {2.5,9.5}
	 */
	@Basic
	@Raw
	@Immutable
	public final double[] getForce() {
		return new double[] {2.5,9.5};
	}

}
