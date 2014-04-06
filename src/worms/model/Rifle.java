package worms.model;

import be.kuleuven.cs.som.annotate.*;

public class Rifle extends Projectile {
	
	
	public Rifle(double coordinateX, double coordinateY, boolean isActive,
			World world, double direction) throws ModelException {
		super(coordinateX, coordinateY, isActive, world, direction,
				10,	20, 10);
	}
	
	/**
	 * Returns the force in Newton at which projectiles from a rifle are propelled.
	 * 
	 * 
	 * @return	Returns the force.
	 * 			| result == 1.5
	 */
	@Basic
	@Raw
	@Immutable
	public final double getForce() {
		return 1.5;
	}

}