package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;


/**
 * A class describing the attributes and actions of a movable object.
 * 
 * @version 2.10
 * @author 	Gertjan Maenhout (2Bbi Computerwetenschappen - Elektrotechniek) & 
 * 			Harald Schafer (2Bbi Elektrotechniek - Computerwetenschappen)
 *
 * @invar	The x coordinate of the movable object must be a valid coordinate.
 * 			| isValidCoordinate(this.getCoordinateX()) 
 * @invar	The y coordinate of the movable object must be a valid coordinate.
 * 			| isValidCoordinate(this.getCoordinateY())
 * @invar	The direction of the movable object must be a valid direction.
 * 			| isValidDirection(this.getDirection())
 * @invar	The direction of the movable object is between 0 and 2Pi radians.
 *			| this.getDirection() >= 0 && this.getDirection() < Math.PI*2  
 * @invar	The radius of the movable object must be a valid radius.
 * 			| this.isValidRadius(this.getRadius())
 * @invar	The movable object should be in a proper world.
 * 			| this.hasProperWorld()
 */
public abstract class MovableObject extends GameObject {

	private double direction;
	
	/**
	 * The constructor to make a movable object.
	 * 
	 * @param 	coordinateX
	 * 			The x coordinate for this new game object.
	 * @param 	coordinateY
	 * 			The y coordinate for this new game object.
	 * @param	radius
	 * 			The radius for this new game object.
	 * @param 	isActive
	 * 			The status for this new game object.
	 * @param 	world
	 * 			The world for this new game object.
	 * @param 	direction
	 * 			The new direction of this movable object.
	 * 
	 * @post	The new x coordinate of this game object will be equal to the given coordinateX.
	 * 			| new.getCoordinateX() == coordinateX
	 * @post	The new y coordinate of this game object will be equal to the given coordinateY.
	 * 			| new.getCoordinateY() == coordinateY
	 * @post	The new status of this game object will be equal to the given isActive.
	 * 			| new.getStatus() == isActive
	 * @post	The new world of this game object will be equal to the given world.
	 * 			| new.getWorld() == world
	 * @post	The new direction of this movable object will be equal to the given direction.
	 * 			| new.getDirection() == changeAngleModulo2PI(direction)
	 * 
	 * @effect	If one of the given coordinates is not in this world, this movable object is terminated.
	 * 			| if (this.isXCoordinateOutOfBounds(coordinateX) || this.isYCoordinateOutOfBounds(coordinateY))
	 * 			|		then this.terminate()
	 * 
	 * @throws	ModelException
	 * 			The exception is thrown if one or more of the given parameters are illegal 
	 * 			assignments for this game object.
	 * 			| (! isValidCoordinate(coordinateX)) || (! isValidCoordinate(coordinateY))
	 * 			|		|| (! isValidRadius(radius)) 
	 */
	protected MovableObject(double coordinateX, double coordinateY, boolean isActive, 
			double radius, World world, double direction) throws ModelException {
		super(coordinateX, coordinateY, isActive, radius,world);
		if (this.getStatus())
			this.setDirection(direction);
	}
	
	
	
	
	/**
	 * Returns the current direction of this movable object.
	 */
	@Basic
	@Raw
	protected double getDirection() {
		return this.direction;
	}
	
	
	/**
	 * Set the direction of this movable object to the given direction.
	 * 
	 * @param 	direction
	 * 		  	The new direction of this movable object.
	 * 
	 * @pre		The given direction must be a valid direction.
	 * 			| isValidDirection(direction)
	 * 
	 * @post	The new direction of this movable object is equal to the given direction.
	 * 			| new.getDirection() == changeAngleModulo2PI(direction)
	 */
	@Raw
	protected void setDirection(double direction){
		assert isValidDirection(direction);
		this.direction = changeAngleModulo2PI(direction);
	}

	
	/**
	 * Returns the modulo 2PI of a given angle.
	 * 
	 * @param 	angle
	 * 			The given angle.
	 * 
	 * @return	Returns the modulo 2PI of the given angle.
	 * 			| angle = angle % (2*Math.PI)
	 * 			| if angle<0
	 * 			| 	then angle = angle + 2*Math.PI
	 * 			| result == angle
	 */
	@Raw
	protected static double changeAngleModulo2PI(double angle) {
		angle = angle % (2*Math.PI);
		if (angle < 0)
			angle = angle + 2*Math.PI;
		return angle;
	}
	
	
	/**
	 * Check whether the given direction is a valid direction for
	 * a movable object.
	 * 
	 * @param  	direction
	 *         	The direction to check.
	 * 
	 * @return 	True if and only if the given direction is a finite number.
	 *			| result == !(Double.isNaN(direction) || Double.isInfinite(Direction))
	 */
	@Raw
	protected static boolean isValidDirection(double direction) {
		return ! (Double.isNaN(direction) || Double.isInfinite(direction));
	}

	
	
	
	/**
	 * Returns the mass of this movable object.
	 */
	protected abstract double getMass();
	
	
	
	
	/**
	 * Make a movable object jump to its new location.
	 * 
	 * @param 	timeStep
	 * 			A time interval in which the movable object will not
	 * 			completely move through impassable terrain.
	 */
	protected abstract void jump(double timeStep);
	

	/**
	 * Returns the time this movable object is in the air during the potential jump of this movable object,
	 * in case that it would land at the same level that it jumped from. 
	 * (This means that the y coordinate remains the same.)
	 * 
	 * @return 	Returns the time in the air.
	 * 			If the direction equals PI/2, then the time in the air will be approximated
	 * 			by assuming that the direction equals PI/2+2^(-1074). The value that is added 
	 * 			is the smallest positive value that the type Double can represent.
	 * 			| if this.getDirection() == Math.PI/2
	 * 			|	then result == this.getJumpDistance()/(this.getJumpVelocity()*Math.cos(Math.PI/2+2^(-1047)))
	 * 			| result == this.getJumpDistance()/(this.getJumpVelocity()*Math.cos(this.getDirection()))
	 */
	protected double getJumpTime(){
		if (this.getDirection() == Math.PI/2)
			return this.getJumpDistance()/(this.getJumpVelocity()*Math.cos(Math.PI/2+Double.MIN_VALUE));
		return this.getJumpDistance()/(this.getJumpVelocity()*Math.cos(this.getDirection()));
	}
	
	
	/**
	 * Returns the initial velocity of a movable object at the moment that it jumps from the ground.
	 */
	protected abstract double getJumpVelocity();
	
	
	/**
	 * Returns the horizontal distance of the potential jump of this movable object.
	 * 
	 * @return	The horizontal distance.
	 * 			| result == Math.pow(this.getJumpVelocity(), 2)*
	 * 			|				Math.sin(2*this.getDirection())/this.getWorld().getGravity()
	 */
	protected double getJumpDistance(){
		return Math.pow(this.getJumpVelocity(), 2)*Math.sin(2*this.getDirection())/this.getWorld().getGravity();
	}
	
	
	/**
	 * Calculates the position of this movable object at a given time in a jump.
	 * 
	 * @param 	time
	 * 			The point in time at which the position should be calculated.
	 * 
	 * @return	Returns the x and y coordinates of the position at which this worm will be at the given time.
	 * 			| initialJumpVelocityX = this.getJumpVelocity()*Math.cos(this.getDirection())
	 * 			| initialJumpVelocityY = this.getJumpVelocity()*Math.sin(this.getDirection())
	 * 			| result == {this.getCoordinateX()+initialJumpVelocityX*time, 
	 * 			| 			 this.getCoordinateY()+initialJumpVelocityY*time-0.5*this.getWorld().getGravity()*Math.pow(time, 2)}
	 * 
	 * @throws	ModelException
	 * 			This worm cannot jump.
	 * 			| (!canJump())
	 */
	protected double[] getJumpStep(double time) throws ModelException {
		if (! this.canJump())
			throw new ModelException("Cannot jump!");
		double initialJumpVelocityX = this.getJumpVelocity()*Math.cos(this.getDirection());
		double initialJumpVelocityY= this.getJumpVelocity()*Math.sin(this.getDirection());
		double[] result = 
		        {this.getCoordinateX()+initialJumpVelocityX*time,
				this.getCoordinateY()+initialJumpVelocityY*time-0.5*this.getWorld().getGravity()*Math.pow(time, 2)};
		return result;
	}
	
	
	/**
	 * Returns the real time that a movable object is in the air.
	 * (In contrast to getJumpTime(), the y coordinate can change.)
	 * 
	 * @param 	step
	 * 			A time interval in which the movable object will not
	 * 			completely move through impassable terrain.
	 */
	protected abstract double getJumpRealTimeInAir(double step);
	
	
	/**
	 * Returns whether or not this movable object is in such a condition, 
	 * that it could jump.
	 */
	protected abstract boolean canJump();
	
	
	
	@Override
	protected MovableObject clone() throws CloneNotSupportedException {
		return (MovableObject) super.clone();
	}
}
