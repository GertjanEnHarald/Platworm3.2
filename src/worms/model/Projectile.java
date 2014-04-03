package worms.model;

import be.kuleuven.cs.som.annotate.*;

public abstract class Projectile extends MoveableObject {
	
	private static final double density = 7800;		// In kg/m³
	private final double massOfProjectile;			// In gram
	private final int lostHitPoints;
	private final int costActionPoints;
	
	//TODO Coordinates an direction determined by coordinates and direction of worm. 
	public Projectile(double coordinateX, double coordinateY, boolean isActive,	double radius, World world,
			double direction, double massOfProjectile, int lostHitPoints, int costActionPoints) 
			throws ModelException {
		super(coordinateX, coordinateY, isActive, radius, world, direction);
		this.massOfProjectile = massOfProjectile;
		this.lostHitPoints = lostHitPoints;
		this.costActionPoints = costActionPoints;
	}
	
	public abstract double getForce();
	
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
	
	/**
	 * Returns the radius of this projectile, derived by the mass and density of this projectile. 
	 * 
	 * 
	 * @return	Returns the mass.
	 * 			| result == (this.getMass()*(3/4)*(1/getDensity())
	 *			|			*(1/Math.PI))^(1/3)
	 */
	public double getRadius() {
		return Math.pow(this.getMass()*(3/4)*(1/getDensity())
				*(1/Math.PI), 1/3);
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
	
	@Override
	public void jump() {
		// TODO Auto-generated method stub
	}

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
	
	
}
