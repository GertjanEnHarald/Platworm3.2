package worms.model;

public class Projectile extends MoveableObject {
	
	
	private Weapon weapon;
	
	
	public Projectile(double coordinateX, double coordinateY, boolean isActive,	double radius, Weapon weapon,World world) 
			throws ModelException {
	super(coordinateX, coordinateY, isActive, radius,world);		
	if (! isValidWeapon(weapon))
		throw new ModelException("Illegal weapon assigned!");
	this.weapon = weapon;
	}

	@Override
	public boolean isValidRadius(double radius) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public static boolean isValidWeapon(Weapon weapon) {
		return ((weapon == Weapon.Bazooka) || (weapon == Weapon.Rifle));
	}
	
	
	public Weapon getWeapon() {
		return this.weapon;
	}
	
	@Override
	public double getRadius() {
		return this.weapon.getRadius();
	}
	
	@Override
	public double getMass() {
		return this.weapon.getMassOfProjectile();
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}
	
}
