package worms.model;

import be.kuleuven.cs.som.annotate.*;

public class Bazooka extends Projectile {
	

	/**
	 * Constructor to make a bazooka.
	 * 
	 * @post	The name of this weapon will be set.
	 * 			| new.getName() == 'Bazooka'
	 */
	public Bazooka(double coordinateX, double coordinateY, boolean isActive,
			World world, double direction) throws ModelException {
		super(coordinateX, coordinateY, isActive, world, direction,
				0.300, 80, 50);
		this.setName("Bazooka");
	}
	
	
	
	
	/**
	 * Returns the force in Newton at which projectiles from 
	 * a bazooka are propelled, respectively to the propulsion yield.
	 * 
	 * 
	 * @return	Returns the force.
	 * 			| result == (2.5 + this.getPropulsionYield()*0.07)
	 */
	@Raw
	public double getForce() {
		return (2.5 + this.getYield()*0.07);
	}

}
