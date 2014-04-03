package worms.model;

import be.kuleuven.cs.som.annotate.*;

public class Projectile extends MoveableObject {
	
	private static final double density = 7800;		// In kg/m³
	private final double massOfProjectile;			// In gram
	private final int lostHitPoints;
	private final int costActionPoints;
	
	public Projectile(double coordinateX, double coordinateY, boolean isActive,	double radius, World world,
			double massOfProjectile, int lostHitPoints, int costActionPoints) 
			throws ModelException {
		super(coordinateX, coordinateY, isActive, radius,world);
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
	public boolean isValidRadius(double radius) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}
	
}
