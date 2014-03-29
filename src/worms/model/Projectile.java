package worms.model;

public class Projectile extends MoveableObject {

	public Projectile(double coordinateX, double coordinateY, boolean isActive,	double radius) {
		super(coordinateX, coordinateY, isActive, radius);
	}

	@Override
	public boolean isValidRadius(double radius) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
