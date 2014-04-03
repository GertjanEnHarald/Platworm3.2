package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public abstract class MoveableObject extends GameObject {

	private double direction;
	
	public MoveableObject(double coordinateX, double coordinateY, boolean isActive, 
			double radius, World world, double direction) {
		super(coordinateX, coordinateY, isActive, radius,world);
	}
	
	
	/**
	 * Returns the current direction of this moveable object.
	 */
	@Basic
	@Raw
	public double getDirection() {
		return this.direction;
	}
	
	
	/**
	 * Set the direction of this moveable object to the given direction.
	 * 
	 * 
	 * @param 	direction
	 * 		  	The new direction of this moveable object.
	 * 
	 * @pre		The given direction must be a valid direction.
	 * 			| isValidDirection(direction)
	 * @post	The new direction of this moveable object is equal to the given direction.
	 * 			| new.getDirection() == changeAngleModulo2PI(direction)
	 */
	@Raw
	public void setDirection(double direction){
		assert isValidDirection(direction);
		this.direction = changeAngleModulo2PI(direction);
	}

	
	/**
	 * Returns the modulo 2PI of a given angle.
	 * 
	 * 
	 * @param 	angle
	 * 			The given angle.
	 * 
	 * @return	Returns the modulo 2PI of the given angle.
	 * 			| angle = angle % (2*Math.PI)
	 * 			| if angle<0
	 * 			| 	angle = angle + 2*Math.PI
	 * 			| result == angle
	 */
	@Raw
	public static double changeAngleModulo2PI(double angle) {
		angle = angle % (2*Math.PI);
		if (angle < 0)
			angle = angle + 2*Math.PI;
		return angle;
	}
	
	
	/**
	 * Check whether the given direction is a valid direction for
	 * a moveable object.
	 * 
	 * 
	 * @param  	direction
	 *         	The direction to check.
	 * 
	 * @return 	True if and only if the given direction is a finite number.
	 *			| result == !(Double.isNaN(direction) || Double.isInfinite(Direction))
	 */
	@Raw
	public static boolean isValidDirection(double direction) {
		return ! (Double.isNaN(direction) || Double.isInfinite(direction));
	}


	public abstract double getMass();
	
	public abstract void jump();
	
	/**
	 * Returns the time this moveable object is in the air during the potential jump of this moveable object.
	 * 
	 * 
	 * @return 	Returns the time in the air.
	 * 			| result == this.getJumpDistance()/(this.getJumpVelocity()*Math.cos(this.getDirection()))
	 */
	public double getJumpTime(){
		return this.getJumpDistance()/(this.getJumpVelocity()*Math.cos(this.getDirection()));
	}
	
	public abstract double getJumpVelocity();
	
	/**
	 * Returns the horizontal distance of the potential jump of this moveable object.
	 * 
	 * 
	 * @return	The horizontal distance.
	 * 			| result == Math.pow(this.getJumpVelocity(), 2)*
	 * 			|				Math.sin(2*this.getDirection())/getGravity()
	 */
	public double getJumpDistance(){
		return Math.pow(this.getJumpVelocity(), 2)*Math.sin(2*this.getDirection())/getGravity();
	}
	
	public double[] getJumpStep(double time) throws ModelException {
		if(time > this.getJumpTime())
			throw new ModelException("Cannot calculate position at time, worm has already landed!");
		double initialJumpVelocityX = this.getJumpVelocity()*Math.cos(this.getDirection());
		double initialJumpVelocityY= this.getJumpVelocity()*Math.sin(this.getDirection());
		double[] result = 
		        {this.getCoordinateX()+initialJumpVelocityX*time,
				this.getCoordinateY()+initialJumpVelocityY*time-0.5*getGravity()*Math.pow(time, 2)};
		return result;
	}
	
}
