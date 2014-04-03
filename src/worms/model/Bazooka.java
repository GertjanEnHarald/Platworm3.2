package worms.model;

import be.kuleuven.cs.som.annotate.*;

public class Bazooka extends Projectile {
	
	private final int propulsionYield;

	public Bazooka(double coordinateX, double coordinateY, boolean isActive,
			double radius, World world, double direction,int propulsionYield) throws ModelException {
		super(coordinateX, coordinateY, isActive, radius, world, direction,
				300, 80, 50);
		if (! isValidPropulsionYield(propulsionYield))
			throw new ModelException("Illegal propulsion yield!");
		this.propulsionYield = propulsionYield;
	}

	public static boolean isValidPropulsionYield(int propulsionYield) {
		return (propulsionYield >= 0) && (propulsionYield <= 100);
	}
	
	/**
	 * Returns the propulsion yield that a worm uses to shoot a projectile.
	 * This propulsion yield determines the force.
	 * 
	 * 
	 * @return	The propulsion yield lies between 0 and 100.
	 * 			| (result >= 0) && (result <= 100)
	 */
	public int getPropulsionYield() {
		return this.propulsionYield;
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
		return (2.5 + this.getPropulsionYield()*0.07);
	}

}
