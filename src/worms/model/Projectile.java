package worms.model;

import be.kuleuven.cs.som.annotate.*;


/**
 * A class describing the attributes and actions of a projectile.
 * 
 * @version 2.7
 * @author 	Gertjan Maenhout (2Bbi Computerwetenschappen - Elektrotechniek) & 
 * 			Harald Schafer (2Bbi Elektrotechniek - Computerwetenschappen)
 * 
 * @invar	The x coordinate of the projectile must be a valid coordinate.
 * 			| isValidCoordinate(this.getCoordinateX()) 
 * @invar	The y coordinate of the projectile must be a valid coordinate.
 * 			| isValidCoordinate(this.getCoordinateY())
 * @invar	The direction of the projectile must be a valid direction.
 * 			| isValidDirection(this.getDirection())
 * @invar	The direction of the projectile is between 0 and 2Pi radians.
 *			| this.getDirection() >= 0 && this.getDirection() < Math.PI*2  
 * @invar	The radius of the projectile must be a valid radius.
 * 			| this.isValidRadius(this.getRadius())
 * @invar	The mass of the projectile must be a valid mass.
 * 			| isValidMass(this.getMass())
 * @invar	The yield of this projectile should be a valid yield.
 * 			| isValidYield(this.getYield())
 * @invar	The projectile should be in a proper world.
 * 			| this.hasProperWorld()
 */
public abstract class Projectile extends MovableObject {
	
	
	/**
	 * The initialisation of the variables.
	 */
	
	/**
	 * The density of the projectile.
	 */
	private static final double density = 7800;		// In kg/m³
	/**
	 * The mass of the projectile.
	 */
	private final double massOfProjectile;			// In kg
	/**
	 * The hit points a worm loses if hit by this projectile.
	 */
	private final int lostHitPoints;
	/**
	 * The action points it costs to fire this projectile.
	 */
	private final int costActionPoints;
	/**
	 * A boolean representing whether this projectile is "alive".
	 */
	private boolean isTerminated;
	/**
	 * The name of this projectile.
	 */
	private String name;
	/**
	 * The propulsion yield of this projectile.
	 */
	private int yield;
	
	
	
	
	/**
	 * The constructor to make a projectile.
	 * 
	 * @param 	coordinateX
	 * 			The x coordinate for this new projectile.
	 * @param 	coordinateY
	 * 			The y coordinate for this new projectile.
	 * @param	radius
	 * 			The radius for this new projectile.
	 * @param 	isActive
	 * 			The status for this new projectile.
	 * @param 	world
	 * 			The world for this new projectile.
	 * @param 	direction
	 * 			The direction for this new projectile.
	 * @param 	massOfProjectile
	 * 			The mass for this new projectile.
	 * @param 	lostHitPoints
	 * 			The amount of hit points that a worm will lose,
	 * 			if it is hit by this projectile.
	 * @param 	costActionPoints
	 * 			The amount of action points that a worm will lose,
	 * 			if it fires this projectile.
	 * 
	 * @post	The new x coordinate of this projectile will be equal to the given coordinateX.
	 * 			| new.getCoordinateX() == coordinateX
	 * @post	The new y coordinate of this projectile will be equal to the given coordinateY.
	 * 			| new.getCoordinateY() == coordinateY
	 * @post	The new status of this projectile will be equal to the given isActive.
	 * 			| new.getStatus() == isActive
	 * @post	The new world of this projectile will be equal to the given world.
	 * 			| new.getWorld() == world
	 * @post	The new mass of this projectile will be equal to the given massOfProjectile.
	 * 			| new.getMass() == massOfProjectile
	 * @post	The new radius of this projectile will be deduced from the density and the mass,
	 * 			assuming that the projectile is a spherical object.
	 * 			| new.getRadius() == getRadius(new.getMass())
	 * @post	The new amount of hit points that a worm will lose, if it is hit
	 * 			by this projectile, will be equal to the given lostHitPoints.
	 * 			| new.getLostHitPoints() == lostHitPoints
	 * @post	The new amount of action points that a worm will lose, if it fires this projectile,
	 * 			will be equal to the given costActionPoints.
	 * 			| new.getCostActionPoints() == costActionPoints
	 * 
	 * @throws 	ModelException
	 * 			The exception is thrown if one or more of the given parameters are illegal 
	 * 			assignments for this projectile.
	 * 			| (! isValidCoordinate(coordinateX)) || (! isValidCoordinate(coordinateY))
	 * 			|		|| (! isValidRadius(radius)) || (! isValidMass(massOfProjectile)) 
	 * 			| 		|| (! isValidLostHitPoints(lostHitPoints)) 
	 * 			|		||  (! isValidCostActionPoints(costActionPoints))
	 */
	protected Projectile(double coordinateX, double coordinateY, boolean isActive,	World world,
			double direction, double massOfProjectile, int lostHitPoints, int costActionPoints) 
			throws ModelException {
		super(coordinateX, coordinateY, isActive, getRadius(massOfProjectile), world, direction);
		if (! isValidMass(massOfProjectile))
			throw new ModelException("Invalid mass assignment!");
		this.massOfProjectile = massOfProjectile;
		if (! isValidLostHitPoints(lostHitPoints))
			throw new ModelException("Invalid amount of lost hit points assignment!");
		this.lostHitPoints = lostHitPoints;
		if (! isValidCostActionPoints(costActionPoints))
			throw new ModelException("Invalid amount of cost action points assignment!");
		this.costActionPoints = costActionPoints;
	}
	
	
	
	
	/**
	 * Returns the density of the projectile in kg/m³.
	 * 
	 * @return	Returns the density.
	 * 			| result == 7800
	 */
	@Basic
	@Raw
	@Immutable
	protected static final double getDensity() {
		return density;
	}
	
	
	
	
	/**
	 * Returns whether or not this projectile is terminated.
	 */
	@Basic
	@Raw
	protected boolean isTerminated() {
		return this.isTerminated;
	}
	
	
	/**
	 * Terminate this projectile.
	 * 
	 * @post	This projectile is now terminated.
	 * 			| new.isTerminated() == true
	 */
	@Raw
	@Override
	protected void terminate() {
		super.terminate();
		this.isTerminated = true;
	}
	
	
	
	
	/**
	 * Returns the name of the weapon that shoots this projectile.
	 */
	@Basic
	@Raw
	protected String getName() {
		return this.name;
	}
	
	
	/**
	 * Set the name of the weapon that shoots this projectile.
	 * 
	 * @param	name
	 * 			The name of this weapon that shoots the projectile.
	 * 
	 * @post	The new name of this weapon will be equal to the given name.
	 * 			| new.getName() == name
	 */
	@Raw
	protected void setName(String name) {
		this.name = name;
	}
	
	
	
	
	/**
	 * Checks whether the given mass is a valid mass.
	 *  
	 * @param 	mass
	 * 			The mass that should be checked
	 * 
	 * @return	Returns if the given mass is a valid mass.
	 * 			The mass should be a positive number.
	 * 			| result == (mass != Double.NaN) && (mass > 0)
	 */
	protected static boolean isValidMass(double mass) {
		return (mass != Double.NaN) && (mass > 0);
	}
	
	
	
	
	/**
	 * Returns the radius of a projectile, derived by the mass and density of a projectile. 
	 * 
	 * @param	mass
	 * 			The mass for which the radius should be calculated.
	 * 
	 * @return	Returns the mass, assumed that the projectile is a spherical object.
	 * 			| result == (mass*(3/4)*(1/getDensity())
	 *			|			*(1/Math.PI))^(1/3)
	 */
	protected static double getRadius(double mass) {
		return Math.pow((3*mass)/(getDensity()*4*Math.PI), 1.0/3);
	}
	
	
	/**
	 * Returns the mass of this projectile, derived by the mass and density of a projectile.
	 * 
	 * @return	Returns the mass of this projectile.
	 * 			| result == getRadius(this.getMass())
	 */
	@Raw
	@Override
	protected double getRadius() {
		return getRadius(this.getMass());
	}
	
	
	/**
	 * Returns if this projectile has a valid radius.
	 * 
	 * @param	radius
	 * 			The radius that needs to be checked.
	 * 
	 * @return	Returns if the radius is positive.
	 * 			| result == (radius > 0)
	 */
	@Override
	protected boolean isValidRadius(double radius) {
		return (radius > 0);
	}
	
	
	
	
	/**
	 * Returns the mass of this projectile in kilogram.
	 */
	@Basic
	@Raw
	@Override
	protected double getMass() {
		return this.massOfProjectile;
	}

	
	
	
	/**
	 * Checks if the given points are a valid amount of lost hit points.
	 *  
	 * @param 	points
	 * 			The points that needs to be checked.
	 * 
	 * @return	Returns whether the given points are positive.
	 * 			| result == (points > 0)
	 */
	@Raw
	protected static boolean isValidLostHitPoints(int points) {
		return (points > 0);
	}
	
	
	/**
	 * Returns the hit points a worm will lose, if he has 
	 * been hit by this projectile.
	 */
	@Basic
	@Raw
	protected int getLostHitPoints() {
		return this.lostHitPoints;
	}
	
	
	
	
	/**
	 * Checks if the given points are a valid amount of cost action points.
	 *  
	 * @param 	points
	 * 			The points that needs to be checked.
	 * 
	 * @return	Returns whether the given points are positive.
	 * 			| result == (points > 0)
	 */
	@Raw
	protected static boolean isValidCostActionPoints(int points) {
		return (points > 0);
	}
	
	
	/**
	 * Returns the action points that a worm will lose, 
	 * when it fires this projectile.
	 */
	@Basic
	@Raw
	protected int getCostActionPoints() {
		return this.costActionPoints;
	}

	
	
	
	/**
	 * Returns the yield of force that is used to propel a projectile.
	 */
	@Basic
	@Raw
	protected int getYield() {
		return this.yield;
	}
	
	
	/**
	 * Set the yield to the given yield.
	 * 
	 * @param 	yield
	 * 			The yield for this projectile.
	 * 
	 * @post	The new yield of this projectile is equal to the given yield.
	 * 			| new.getYield() == yield
	 * 
	 * @throws 	ModelException
	 * 			If the given yield is an invalid yield, the exception is thrown.
	 * 			| (! isValidYield(yield))
	 */
	@Raw
	protected void setYield(int yield) throws ModelException {
		if (! isValidYield(yield))
			throw new ModelException("Invalid yield!");
		this.yield = yield;
	}
	
	
	/**
	 * Checks if the given yield is a valid yield.
	 * 
	 * @param 	yield
	 * 			The yield that needs to be checked.
	 * 
	 * @return	Returns if the yield is between 0 and 100.
	 * 			| result == ((0 <= yield) && (yield <= 100))
	 */
	@Raw
	protected static boolean isValidYield(int yield) {
		return (yield >= 0) && (yield <= 100);
	}
	
	
	
	
	/**
	 * Calculates the time that this worm will be in the air.
	 * 
	 * @param	step
	 * 			A time interval during which the worm will not move completely trough impassable terrain.
	 * 
	 * @return	Returns the time that this projectile is in the air until it hits impassable terrain
	 * 			or it hits another worm. If the worm leaves the world, extra time is provided to make 
	 * 			the worm visually disappear.
	 * 			| for each time in {t | t in 0..result & t = n*step (with n integer)}
	 * 			|	position = this.getJumpStep(t)
	 * 			|	(this.getWorld().isPassableArea(position[0],position[1], this.getRadius())
	 * 			|		&& ! this.getWorld().coordinatesOverlapsWorm(position[0]-0.5*this.getRadius(),
	 * 			|					position[1]-0.5*this.getRadius(),this.getRadius()) ) == true
	 * 			| 
	 * 			| nextPosition = this.getJumpStep(result+step)
	 * 			| (! this.getWorld().isPassableArea(nextPosition[0], nextPosition[1], this.getRadius()) 
	 * 			|		|| this.getWorld().coordinatesOverlapsWorm(nextPosition[0]-0.5*this.getRadius(),
	 * 			|					nextPosition[1]-0.5*this.getRadius(),this.getRadius()) )  == true
	 * 			|
	 * 			| if(! this.getWorld().isInWorld(position[0], position[1], this.getRadius()))
	 * 			|	then result = result + 0.20 
	 */
	@Override
	protected double getJumpRealTimeInAir(double step) {
		double time = 0.0;
		double radius = this.getRadius();
		step = 10.0*step;
		boolean hasLanded = false;
		
		for (double t = step; (! hasLanded) ; t = t + step) {
			double[] position = this.getJumpStep(t);
			if (this.getWorld().isPassableArea(position[0], position[1], radius)
					&& ! this.getWorld().coordinatesOverlapsWorm(position[0]-0.5*radius,position[1]-0.5*radius,radius)) {
				time = t;
				}
			else if(! this.getWorld().isInWorld(position[0], position[1], radius)) {
				time = time+0.20;
				hasLanded = true;
			}
			else {
				hasLanded = true;}
		}
		
		return time;
	}
	
	

	/**
	 * Changes the position of this projectile as the result of a jump in the current direction
	 * of this projectile. The direction does not change during the jump.
	 * 
	 * @param	timeStep
	 * 			A time interval during which this projectile will not move completely trough impassable terrain.
	 * 
	 * @effect	The new x and y coordinates are assigned to this projectile. The end position of the jump 
	 * 			is calculated by getting the step at the last position of the jump.
	 * 			| position = this.getJumpStep(this.getJumpRealTimeInAir(10^-5))
	 * 			| this.setCoordinates(position[0], position[1])
	 * @effect	If a worm is hit, it will lose the amount of hit points specific to this projectile.
	 * 			| if (this.getWorld().projectileOverlapsWorm(this))
	 * 			|		target = this.getWormThatOverlaps(this)
	 * 			|		target.setHitPoints(target.getHitpoints() - this.getLostHitpoints())
	 * 
	 * @throws	ModelException
	 * 			The exception is thrown if this worm cannot jump in its current situation.
	 * 			| ! this.canJump()
	 */
	@Override
	protected void jump(double timeStep) throws ModelException {
		if (! this.canJump()) 
			throw new ModelException("Cannot jump!");

		double[] position = this.getJumpStep(this.getJumpRealTimeInAir(Math.pow(10, -6)));
		this.setCoordinates(position[0], position[1]);
		
		Worm target = this.getWorld().getWormThatOverlaps(this);
		if (target != null) {
			target.setHitPoints(target.getHitPoints()-this.getLostHitPoints());
		}
	}

	
	
	
	/**
	 * Returns the force that is used to propel a projectile.
	 */
	protected abstract double getForce();
	
	
	
	
	/**
	 * Returns the initial velocity of the potential jump of this projectile.
	 * 
	 * @return	The initial velocity of the jump.
	 *			| result == this.getForce()*0.5/this.getMass()
	 */
	@Override
	protected double getJumpVelocity(){
		return this.getForce()*0.5/this.getMass();
	}
	
	
	/**
	 * Returns whether or not this projectile can jump in its current situation.
	 * 
	 * @return	This projectile can jump if it is not terminated.
	 * 			| result == (! this.isTerminated())
	 */
	@Override
	protected boolean canJump() {
		return (! this.isTerminated());
	}
	
	
	
	
	@Override
	protected Projectile clone() throws CloneNotSupportedException {
		return (Projectile) super.clone();
	}
}
