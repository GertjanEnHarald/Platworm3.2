package worms.model;
import be.kuleuven.cs.som.annotate.*;

public enum Weapon {
	
	
	Rifle(10, new double[] {1.5}, 20, 10), 
	Bazooka(300, new double[] {2.5, 9.5}, 80, 50);
	
	
	private static final double density = 7800;		// In kg/m³
	private final double massOfProjectile;			// In gram
	private final double[] force;					// In Newton
	private final int deducedHitPoints;
	private final int costActionPoints;
	
	
	private Weapon(double massOfProjectile, double[] force, int deducedHitPoints, int costActionPoints) {
		this.massOfProjectile = massOfProjectile;
		this.force = force;
		this.deducedHitPoints = deducedHitPoints;
		this.costActionPoints = costActionPoints;
	}
	
	/**
	 * Returns the mass of the projectile of this weapon.
	 */
	@Basic
	@Immutable
	@Raw
	public double getMassOfProjectile() {
		return this.massOfProjectile;
	}
	
	
	/**
	 * Returns the force used to propel the projectile of this weapon.
	 * This could be a single number or an interval that contains all the possible forces.
	 */
	@Basic
	@Immutable
	@Raw
	public double[] getForce() {
		return this.force;
	}
	
	
	/**
	 * Returns the hit points that will be lost if a worm is hit by a projectile of this weapon.
	 */
	@Basic
	@Immutable
	@Raw
	public double getDeducedHitPoints() {
		return this.deducedHitPoints;
	}
	
	
	/**
	 * Returns the action points that will be deduced if a worm shoots this weapon.
	 */
	@Basic
	@Immutable
	@Raw
	public double getCostActionPoints() {
		return this.costActionPoints;
	}
	
	
	/**
	 * Returns the density of a projectile, expressed in kg/m³.
	 */
	@Basic
	@Immutable
	@Raw
	public static double getDensity() {
		return density;
	}
	
	
	/**
	 * Returns the radius of this projectile, derived by the mass and density of this projectile. 
	 * 
	 * 
	 * @return	Returns the mass.
	 * 			| result == Math.pow(this.getMassOfProjectile()*(3/4)*(1/getDensity())
	 *			|			*(1/Math.PI), 1/3)
	 */
	public double getRadius() {
		return Math.pow(this.getMassOfProjectile()*(3/4)*(1/getDensity())
				*(1/Math.PI), 1/3);
	}
	
	
}
