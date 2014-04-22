package worms.model;

import be.kuleuven.cs.som.annotate.*;


/**
 * A class describing the attributes and actions of a rifle.
 * 
 * @version 2.4
 * @author 	Gertjan Maenhout (2Bbi Computerwetenschappen - Elektrotechniek) & 
 * 			Harald Schafer (2Bbi Elektrotechniek - Computerwetenschappen)
 * 
 * @invar	The x coordinate of the rifle must be a valid coordinate.
 * 			| isValidCoordinate(this.getCoordinateX()) 
 * @invar	The y coordinate of the rifle must be a valid coordinate.
 * 			| isValidCoordinate(this.getCoordinateY())
 * @invar	The direction of the rifle must be a valid direction.
 * 			| isValidDirection(this.getDirection())
 * @invar	The direction of the rifle is between 0 and 2Pi radians.
 *			| this.getDirection() >= 0 && this.getDirection() < Math.PI*2  
 * @invar	The radius of the rifle must be a valid radius.
 * 			| this.isValidRadius(this.getRadius())
 * @invar	The mass of the rifle must be a valid mass.
 * 			| isValidMass(this.getMass())
 * @invar	The yield of this rifle should be a valid yield.
 * 			| isValidYield(this.getYield())
 * @invar	The rifle should be in a proper world.
 * 			| this.hasProperWorld()
 */
public class Rifle extends Projectile {
	
	/**
	 * Constructor to make a rifle.
	 * 
	 * @post	The name of this weapon will be set.
	 * 			| new.getName() == 'Rifle'
	 */
	protected Rifle(double coordinateX, double coordinateY, boolean isActive,
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
	protected final double getForce() {
		return 1.5;
	}

	
	@Override
	protected Rifle clone() throws CloneNotSupportedException {
		return (Rifle) super.clone();
	}
	
}
