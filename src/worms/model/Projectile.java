package worms.model;

import be.kuleuven.cs.som.annotate.*;

public abstract class Projectile extends MovableObject {
	
	
	/**
	 * The initialisation of the variables.
	 */
	private static final double density = 7800;		// In kg/m³
	private final double massOfProjectile;			// In kg
	private final int lostHitPoints;
	private final int costActionPoints;
	private boolean isTerminated;
	private String name;
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
	public Projectile(double coordinateX, double coordinateY, boolean isActive,	World world,
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
	public static final double getDensity() {
		return density;
	}
	
	
	
	
	/**
	 * Returns whether or not this projectile is terminated.
	 */
	@Basic
	@Raw
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	
	/**
	 * Terminate this projectile.
	 * 
	 * @post	This projectile is now terminated.
	 * 			| new.isTerminated() == true
	 */
	@Raw
	public void terminate() {
		this.isTerminated = true;
	}
	
	
	
	
	/**
	 * Returns the name of the weapon that shoots this projectile.
	 */
	@Basic
	@Raw
	public String getName() {
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
	public static boolean isValidMass(double mass) {
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
	public static double getRadius(double mass) {
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
	public double getRadius() {
		return getRadius(this.getMass());
	}
	
	
	/**
	 * TODO
	 */
	@Override
	public boolean isValidRadius(double radius) {
		return true;
	}
	
	
	
	
	/**
	 * Returns the mass of this projectile in kilogram.
	 */
	@Basic
	@Raw
	@Override
	public double getMass() {
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
	public static boolean isValidLostHitPoints(int points) {
		return (points > 0);
	}
	
	
	/**
	 * Returns the hit points a worm will lose, if he has 
	 * been hit by this projectile.
	 */
	@Basic
	@Raw
	public int getLostHitPoints() {
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
	public static boolean isValidCostActionPoints(int points) {
		return (points > 0);
	}
	
	
	/**
	 * Returns the action points that a worm will lose, 
	 * when it fires this projectile.
	 */
	@Basic
	@Raw
	public int getCostActionPoints() {
		return this.costActionPoints;
	}

	
	
	
	/**
	 * Returns the yield of force that is used to propel a projectile.
	 */
	@Basic
	@Raw
	public int getYield() {
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
	public static boolean isValidYield(int yield) {
		return (yield >= 0) && (yield <= 100);
	}
	
	
	
	
	/**
	 * TODO
	 */
	@Override
	public double getJumpRealTimeInAir(double step) {
		double maxTime = this.getJumpTime();
		double time = 0.0;
		double radius = this.getRadius();
		step = 150.0*step;

		for (double t = 0; t <= 3.0*maxTime; t = t + step) {
			time = t;
			double[] position = this.getJumpStep(t);
			if ( (! this.getWorld().isPassableArea(position[0], position[1], radius)) ||
					this.getWorld().projectileOverlapsWorm(this)) {
				break;
			}
		}
		return time;
	}
	
	
	/**
	 * TODO
	 */
	@Override
	public void jump(double timeStep) {
		if (! this.canJump()) 
			throw new ModelException("Cannot jump!");
		
		double x = this.getCoordinateX();
		double y = this.getCoordinateY();
		double radiusProjectile = this.getRadius();

		for (double time = timeStep; true; time = time + timeStep) {
			double[] position = this.getJumpStep(time);
			x = position[0];
			y = position[1];
			this.setCoordinates(x, y);
			if ((! this.getWorld().isPassableArea(x, y, radiusProjectile)) || 
					this.getWorld().projectileOverlapsWorm(this)) {
				break;
			}
			else if (! this.getWorld().isInWorld(x, y, this.getRadius())) {
				x = x - this.getRadius();
				y = y - this.getRadius();
				break;
			}
		}	
		
		Worm target = this.getWorld().getWormThatOverlaps(this);
		if (target != null) {
			target.setHitPoints(target.getHitPoints()-this.getLostHitPoints());
		}
	}

	
	
	
	/**
	 * Returns the force that is used to propel a projectile.
	 */
	public abstract double getForce();
	
	
	
	
	/**
	 * Returns the initial velocity of the potential jump of this projectile.
	 * 
	 * @return	The initial velocity of the jump.
	 *			| result == this.getForce()*0.5/this.getMass()
	 */
	@Override
	public double getJumpVelocity(){
		return this.getForce()*0.5/this.getMass();
	}
	
	
	/**
	 * TODO
	 */
	@Override
	public boolean canJump() {
		return true;
	}
	
	
	
	
	@Override
	protected Projectile clone() throws CloneNotSupportedException {
		return (Projectile) super.clone();
	}
}
