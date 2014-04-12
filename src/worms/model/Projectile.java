package worms.model;

import be.kuleuven.cs.som.annotate.*;

public abstract class Projectile extends MovableObject {
	
	private static final double density = 7800;		// In kg/m³
	private final double massOfProjectile;			// In gram
	private final int lostHitPoints;
	private final int costActionPoints;
	private boolean isTerminated;
	private String name;
	private int yield;
	
	
	
	public Projectile(double coordinateX, double coordinateY, boolean isActive,	World world,
			double direction, double massOfProjectile, int lostHitPoints, int costActionPoints) 
			throws ModelException {
		super(coordinateX, coordinateY, isActive, getRadius(massOfProjectile), world, direction);
		this.massOfProjectile = massOfProjectile;
		this.lostHitPoints = lostHitPoints;
		this.costActionPoints = costActionPoints;
	}
	
	/**
	 * Returns the density of the projectile in kg/m³.
	 * 
	 * 
	 * @return	Returns the density.
	 * 			| result > 0
	 */
	@Basic
	@Raw
	public static double getDensity() {
		return density;
	}
	
	public boolean isTerminated() {
		return (! this.isTerminated);
	}
	
	public void terminate() {
		this.isTerminated = true;
	}
	
	public String getName() {
		return this.name;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the radius of this projectile, derived by the mass and density of this projectile. 
	 * 
	 * 
	 * @return	Returns the mass.
	 * 			| result == (this.getMass()*(3/4)*(1/getDensity())
	 *			|			*(1/Math.PI))^(1/3)
	 */
	private static double getRadius(double mass) {
		return Math.pow(mass*(3/4)*(1/getDensity())
				*(1/Math.PI), 1/3);
	}
	
	public double getRadius() {
		return getRadius(this.getMass());
	}
	
	@Override
	public boolean isValidRadius(double radius) {
		return true;
	}
	
	/**
	 * Returns the mass of this projectile in gram.
	 * 
	 * 
	 * @return	Returns the mass.
	 * 			| result > 0
	 */
	@Basic
	@Raw
	@Override
	public double getMass() {
		return this.massOfProjectile;
	}

	/**
	 * Returns the hit points a worm will lose, if he has 
	 * been hit by this projectile.
	 * 
	 * 
	 * @return	Returns the lost hit points.
	 * 			| result > 0
	 */
	@Basic
	@Raw
	public int getLostHitPoints() {
		return this.lostHitPoints;
	}
	
	/**
	 * Returns the action points that a worm will lose, 
	 * when it fires this projectile.
	 * 
	 * 
	 * @return	Returns the cost of action points.
	 * 			| result > 0
	 */
	public int getCostActionPoints() {
		return this.costActionPoints;
	}

	public int getYield() {
		return this.yield;
	}
	
	protected void setYield(int yield) throws ModelException {
		if (! isValidYield(yield))
			throw new ModelException("Invalid yield!");
		this.yield = yield;
	}
	
	public static boolean isValidYield(int yield) {
		return (yield >= 0) && (yield <= 100);
	}
	
	@Override
	public double getJumpRealTimeInAir(double step) {
		double maxTime = this.getJumpTime();
		double time = 0.0;

		for (double t = 0; t <= maxTime - step; t = t + step) {
			double[] position = this.getJumpStep(time);
			time = t;
			if (this.getWorld().isAdjacent(position[0], position[1], this.getRadius())) {
				double[] position2 = this.getJumpStep(time + step);
				if (! this.getWorld().isPassableArea(position2[0], position2[1], this.getRadius())) {
					break;
				}
			}
		}
		return time;
	}
	
	@Override
	public void jump(double timeStep) {
		if (! this.canJump()) 
			throw new ModelException("Cannot jump!");
		System.out.println("Initial X "+this.getCoordinateX());
		System.out.println("Initial Y "+this.getCoordinateY());
		
		
		double x = this.getCoordinateX();
		double y = this.getCoordinateY();
		int counter = 0; 
		System.out.println("Jumptime "+this.getJumpTime());
		System.out.println("Timestep "+timeStep);
		System.out.println("NB " + this.getJumpTime()/timeStep);
		
		for (double time = timeStep; time <= this.getJumpTime() - timeStep; time = time + timeStep) {
			double[] position = this.getJumpStep(time);
			x = position[0];
			y = position[1];
			counter = counter +1;
			if (! this.getWorld().isPassableArea(x, y, this.getRadius()) || 
					this.getWorld().projectileOverlapsWorm(this)) {
				System.out.println("Loop broke in projectile" + counter);
				break;
				}
			}
		System.out.println("Counter "+counter);
		
		this.setCoordinates(x, y);
		System.out.println("X "+this.getCoordinateX());
		System.out.println("Y "+this.getCoordinateY());
		
		System.out.println("Target selected");
		Worm target = this.getWorld().getWormThatOverlaps(this);
		if (target != null) {
			System.out.println("HIT!");
			target.setHitPoints(target.getHitPoints()-this.getLostHitPoints());
		}
	}

	public abstract double getForce();
	
	/**
	 * Returns the initial velocity of the potential jump of this projectile.
	 * 
	 * 
	 * @return	The initial velocity of the jump.
	 *			| result == this.getForce()*0.5/this.getMass()
	 */
	@Override
	public double getJumpVelocity(){
		return this.getForce()*0.5/this.getMass();
	}
	
	@Override
	public boolean canJump() {
		// TODO
		return true;
	}
	
	
}
