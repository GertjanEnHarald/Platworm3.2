package worms.model;

import be.kuleuven.cs.som.annotate.*;

public class Rifle extends Projectile {
	
	/**
	 * Constructor to make a rifle.
	 * 
	 * @post	The name of this weapon will be set.
	 * 			| new.getName() == 'Rifle'
	 */
	public Rifle(double coordinateX, double coordinateY, boolean isActive,
			World world, double direction) throws ModelException {
		super(coordinateX, coordinateY, isActive, world, direction,
				0.010,	20, 10);
		this.setName("Rifle");
	}
	
	/**
	 * Returns the force in Newton at which projectiles from a rifle are propelled.
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

	
	@Override
	protected Rifle clone() throws CloneNotSupportedException {
		return (Rifle) super.clone();
	}
	
}
