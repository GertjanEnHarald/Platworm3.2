package worms.model;

import be.kuleuven.cs.som.annotate.*;


/**
 * A class describing the attributes and actions of a bazooka.
 * 
 * @version 2.7
 * @author 	Gertjan Maenhout (2Bbi Computerwetenschappen - Elektrotechniek) & 
 * 			Harald Schafer (2Bbi Elektrotechniek - Computerwetenschappen)
 * 
 * @invar	The x coordinate of the bazooka must be a valid coordinate.
 * 			| isValidCoordinate(this.getCoordinateX()) 
 * @invar	The y coordinate of the bazooka must be a valid coordinate.
 * 			| isValidCoordinate(this.getCoordinateY())
 * @invar	The direction of the bazooka must be a valid direction.
 * 			| isValidDirection(this.getDirection())
 * @invar	The direction of the bazooka is between 0 and 2Pi radians.
 *			| this.getDirection() >= 0 && this.getDirection() < Math.PI*2  
 * @invar	The radius of the bazooka must be a valid radius.
 * 			| this.isValidRadius(this.getRadius())
 * @invar	The mass of the bazooka must be a valid mass.
 * 			| isValidMass(this.getMass())
 * @invar	The yield of this bazooka should be a valid yield.
 * 			| isValidYield(this.getYield())
 * @invar	The bazooka should be in a proper world.
 * 			| this.hasProperWorld()
 */
public class Bazooka extends Projectile {
	

	/**
	 * Constructor to make a bazooka.
	 * 
	 * @post	The name of this weapon will be set.
	 * 			| new.getName() == 'Bazooka'
	 */
	protected Bazooka(double coordinateX, double coordinateY, boolean isActive,
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
	protected double getForce() {
		return (2.5 + this.getYield()*0.07);
	}

	
	@Override
	protected Bazooka clone() throws CloneNotSupportedException {
		return (Bazooka) super.clone();
	}
}
