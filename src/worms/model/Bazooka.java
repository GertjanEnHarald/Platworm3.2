package worms.model;

import be.kuleuven.cs.som.annotate.*;

public class Bazooka extends Projectile {
	

	public Bazooka(double coordinateX, double coordinateY, boolean isActive,
			World world, double direction) throws ModelException {
		super(coordinateX, coordinateY, isActive, world, direction,
				0.300, 80, 50);
		this.setName("Bazooka");
	}

	public static boolean isValidPropulsionYield(int propulsionYield) {
		return (propulsionYield >= 0) && (propulsionYield <= 100);
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
